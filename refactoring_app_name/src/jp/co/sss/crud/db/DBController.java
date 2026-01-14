package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void findAllEmployees() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);
			resultSet = preparedStatement.executeQuery();

			// ResultSet → DTO(List<Employee>) へ詰め替え
			List<Employee> employeeList = new ArrayList<>();
			while (resultSet.next()) {
				employeeList.add(createEmployeeFromResultSet(resultSet));
			}

			if (employeeList.isEmpty()) {
				System.out.println(ConstantMsg.MSG_NO_RESULT);
				return;
			}

			// DTOから表示
			System.out.println(ConstantMsg.MSG_EMPLOYEE_HEADER);
			for (Employee employee : employeeList) {
				System.out.print(employee.getEmployeeId() + "\t");
				System.out.print(employee.getEmployeeName() + "\t");
				System.out.print(getGenderLabel(employee.getGenderCode()) + "\t");
				System.out.print(employee.getBirthdayString() + "\t");
				System.out.println(employee.getDepartmentName());
			}

			System.out.println("");

		} finally {
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * コンソール入力された社員名で社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByNameFromConsole() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		// 検索キーワード（社員名）
		String employeeNameKeyword = consoleReader.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBManager.getConnection();

			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, "%" + employeeNameKeyword + "%");

			resultSet = preparedStatement.executeQuery();

			// ResultSet → DTO(List<Employee>) へ詰め替え
			List<Employee> employeeList = new ArrayList<>();
			while (resultSet.next()) {
				employeeList.add(createEmployeeFromResultSet(resultSet));
			}

			if (employeeList.isEmpty()) {
				System.out.println(ConstantMsg.MSG_NO_RESULT);
				return;
			}

			System.out.println(ConstantMsg.MSG_EMPLOYEE_HEADER);
			for (Employee employee : employeeList) {
				System.out.print(employee.getEmployeeId() + "\t");
				System.out.print(employee.getEmployeeName() + "\t");
				System.out.print(getGenderLabel(employee.getGenderCode()) + "\t");
				System.out.print(employee.getBirthdayString() + "\t");
				System.out.println(employee.getDepartmentName());
			}

			System.out.println("");

		} finally {
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @param departmentIdString 部署ID（文字列）
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByDepartmentId(String departmentIdString)
			throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBManager.getConnection();

			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, Integer.parseInt(departmentIdString));

			resultSet = preparedStatement.executeQuery();

			// ResultSet → DTO(List<Employee>) へ詰め替え
			List<Employee> employeeList = new ArrayList<>();
			while (resultSet.next()) {
				employeeList.add(createEmployeeFromResultSet(resultSet));
			}

			if (employeeList.isEmpty()) {
				System.out.println(ConstantMsg.MSG_NO_RESULT);
				return;
			}

			System.out.println(ConstantMsg.MSG_EMPLOYEE_HEADER);

			// ※元コードの「部署表示ロジック」を挙動維持のためそのまま再現（dept_name を使わず dept_id を見て表示）
			for (Employee employee : employeeList) {
				System.out.print(employee.getEmployeeId() + "\t");
				System.out.print(employee.getEmployeeName() + "\t");
				System.out.print(getGenderLabel(employee.getGenderCode()) + "\t");
				System.out.print(employee.getBirthdayString() + "\t");

				Integer departmentIdInResult = employee.getDepartmentId();
				Integer genderCode = employee.getGenderCode();

				if (departmentIdInResult != null && departmentIdInResult == ConstantValue.DEPT_SALES) {
					System.out.println(ConstantMsg.MSG_DEPT_SALES);
				} else if (departmentIdInResult != null && departmentIdInResult == ConstantValue.DEPT_ACCOUNTING) {
					System.out.println(ConstantMsg.MSG_DEPT_ACCOUNTING);
				} else if (genderCode != null && genderCode == ConstantValue.DEPT_GENERAL_AFFAIRS) {
					// ※元コードの条件(genderCode == 3)の挙動を変えないため、そのまま定数化のみ実施
					System.out.println(ConstantMsg.MSG_DEPT_GENERAL_AFFAIRS);
				} else {
					System.out.println("");
				}
			}

			System.out.println("");

		} finally {
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 *
	 * @param employeeName       社員名
	 * @param genderCodeString   性別（文字列）
	 * @param birthdayString     生年月日（文字列）
	 * @param departmentIdString 部署ID（文字列）
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 * @throws ParseException
	 */
	public static void insertEmployee(String employeeName, String genderCodeString, String birthdayString,
			String departmentIdString) throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			preparedStatement.setString(1, employeeName);
			preparedStatement.setInt(2, Integer.parseInt(genderCodeString));
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantValue.DATE_PATTERN_YYYYMMDD_SLASH);
			preparedStatement.setObject(3, sdf.parse(birthdayString), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(departmentIdString));

			preparedStatement.executeUpdate();

			System.out.println(ConstantMsg.MSG_INSERT_COMPLETE);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新（更新内容はコンソール入力）
	 *
	 * @param employeeIdString 社員ID（文字列）
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 * @throws ParseException
	 */
	public static void updateEmployeeFromConsole(String employeeIdString)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		try {
			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(ConstantMsg.MSG_INPUT_EMP_NAME);
			String employeeName = consoleReader.readLine();

			System.out.print(ConstantMsg.MSG_INPUT_GENDER);
			String genderCodeString = consoleReader.readLine();

			System.out.print(ConstantMsg.MSG_INPUT_BIRTHDAY);
			String birthdayString = consoleReader.readLine();

			System.out.print(ConstantMsg.MSG_INPUT_DEPT_ID_SEARCH);
			String departmentIdString = consoleReader.readLine();

			preparedStatement.setString(1, employeeName);
			preparedStatement.setInt(2, Integer.parseInt(genderCodeString));
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantValue.DATE_PATTERN_YYYYMMDD_SLASH);
			preparedStatement.setObject(3, sdf.parse(birthdayString), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(departmentIdString));
			preparedStatement.setInt(5, Integer.parseInt(employeeIdString));

			preparedStatement.executeUpdate();

		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除（社員IDはコンソール入力）
	 */
	public static void deleteEmployeeFromConsole() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		try {
			connection = DBManager.getConnection();

			String employeeIdString = consoleReader.readLine();

			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);
			preparedStatement.setInt(1, Integer.parseInt(employeeIdString));
			preparedStatement.executeUpdate();

			System.out.println(ConstantMsg.MSG_DELETE_COMPLETE);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 3:DTO の導入 

	/**
	 * ResultSet から Employee DTO を生成
	 */
	private static Employee createEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
		Employee employee = new Employee();

		employee.setEmployeeId(resultSet.getInt("emp_id"));
		employee.setEmployeeName(resultSet.getString("emp_name"));
		employee.setGenderCode(resultSet.getInt("gender"));
		employee.setBirthdayString(resultSet.getString("birthday"));
		employee.setDepartmentName(resultSet.getString("dept_name"));

		// SQLによって dept_id が含まれない場合があるため、取得できるときのみセット
		try {
			int departmentId = resultSet.getInt("dept_id");
			if (!resultSet.wasNull()) {
				employee.setDepartmentId(departmentId);
			}
		} catch (SQLException e) {
			// dept_id が無いSQLの場合は何もしない
		}

		return employee;
	}

	/**
	 * 性別コード → 表示文字列
	 */
	private static String getGenderLabel(Integer genderCode) {
		if (genderCode == null) {
			return "";
		}
		if (genderCode == ConstantValue.GENDER_NO_ANSWER) {
			return ConstantMsg.MSG_GENDER_NO_ANSWER;
		} else if (genderCode == ConstantValue.GENDER_MALE) {
			return ConstantMsg.MSG_GENDER_MALE;
		} else if (genderCode == ConstantValue.GENDER_FEMALE) {
			return ConstantMsg.MSG_GENDER_FEMALE;
		} else if (genderCode == ConstantValue.GENDER_OTHER) {
			return ConstantMsg.MSG_GENDER_OTHER;
		}
		return "";
	}
}
