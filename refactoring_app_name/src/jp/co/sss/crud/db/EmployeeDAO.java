package jp.co.sss.crud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 社員テーブルのDB操作を行うDAO
 */
public class EmployeeDAO implements IEmployeeDAO {

	/** 全件検索してEmployeeリストを返す */
	@Override
	public List<Employee> findAll() throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			List<Employee> employeeList = new ArrayList<>();
			while (resultSet.next()) {
				employeeList.add(mapEmployee(resultSet));
			}
			return employeeList;

		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/** 社員名で検索してEmployeeリストを返す */
	@Override
	public List<Employee> findByEmployeeName(String searchName) throws SystemErrorException {
		String sql = ConstantSQL.SQL_SELECT_BASIC + ConstantSQL.SQL_SELECT_BY_EMP_NAME;

		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, "%" + searchName + "%");

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				List<Employee> employeeList = new ArrayList<>();
				while (resultSet.next()) {
					employeeList.add(mapEmployee(resultSet));
				}
				return employeeList;
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/** 部署IDで検索してEmployeeリストを返す */
	@Override
	public List<Employee> findByDeptId(int deptId) throws SystemErrorException {
		String sql = ConstantSQL.SQL_SELECT_BASIC + ConstantSQL.SQL_SELECT_BY_DEPT_ID;

		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, deptId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				List<Employee> employeeList = new ArrayList<>();
				while (resultSet.next()) {
					employeeList.add(mapEmployee(resultSet));
				}
				return employeeList;
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/** Employeeの内容で新規登録する（社員IDは自動採番） */
	@Override
	public void insert(Employee employee) throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT)) {

			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setInt(2, employee.getGenderCode());

			SimpleDateFormat sdf = new SimpleDateFormat(ConstantValue.DATE_PATTERN_YYYYMMDD_SLASH);
			preparedStatement.setObject(3, sdf.parse(employee.getBirthdayString()), Types.DATE);

			preparedStatement.setInt(4, employee.getDepartmentId());
			preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException | java.text.ParseException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/** Employeeの内容で更新し、更新件数を返す */
	@Override
	public Integer update(Employee employee) throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE)) {

			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setInt(2, employee.getGenderCode());

			SimpleDateFormat sdf = new SimpleDateFormat(ConstantValue.DATE_PATTERN_YYYYMMDD_SLASH);
			preparedStatement.setObject(3, sdf.parse(employee.getBirthdayString()), Types.DATE);

			preparedStatement.setInt(4, employee.getDepartmentId());
			preparedStatement.setInt(5, employee.getEmployeeId());

			return preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException | java.text.ParseException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/** 社員IDで削除し、削除件数を返す */
	@Override
	public Integer delete(Integer empId) throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE)) {

			preparedStatement.setInt(1, empId);
			return preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/** ResultSetの1行をEmployee DTOに変更 */
	private Employee mapEmployee(ResultSet resultSet) throws SQLException {
		Employee employee = new Employee();

		employee.setEmployeeId(resultSet.getInt("emp_id"));
		employee.setEmployeeName(resultSet.getString("emp_name"));
		employee.setGenderCode(resultSet.getInt("gender"));
		employee.setBirthdayString(resultSet.getString("birthday"));
		employee.setDepartmentName(resultSet.getString("dept_name"));

		// SQLによって dept_id が含まれない場合があるため安全に取得
		try {
			int deptId = resultSet.getInt("dept_id");
			if (!resultSet.wasNull()) {
				employee.setDepartmentId(deptId);
			}
		} catch (SQLException e) {
			// dept_idが無いSQLなので未設定のまま
		}

		return employee;
	}
}
