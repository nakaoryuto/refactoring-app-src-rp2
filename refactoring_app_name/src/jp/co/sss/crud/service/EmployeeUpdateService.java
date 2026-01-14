package jp.co.sss.crud.service;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.db.IEmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeBirthdayReader;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.EmployeeEmpIdReader;
import jp.co.sss.crud.io.EmployeeGenderReader;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.io.IConsoleReader;
import jp.co.sss.crud.util.ConstantMsg;

public class EmployeeUpdateService implements IEmployeeService {

	private final IEmployeeDAO employeeDAO = new EmployeeDAO();
	private final ConsoleWriter writer = new ConsoleWriter();

	private final IConsoleReader empIdReader = new EmployeeEmpIdReader();
	private final IConsoleReader nameReader = new EmployeeNameReader();
	private final IConsoleReader genderReader = new EmployeeGenderReader();
	private final IConsoleReader birthdayReader = new EmployeeBirthdayReader();
	private final IConsoleReader deptReader = new EmployeeDeptIdReader();

	/** 社員IDを指定して社員情報を更新する */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		writer.prompt(ConstantMsg.MSG_INPUT_UPDATE_EMP_ID);
		int empId = (Integer) empIdReader.input();

		writer.prompt(ConstantMsg.MSG_INPUT_EMP_NAME);
		String employeeName = (String) nameReader.input();

		writer.prompt(ConstantMsg.MSG_INPUT_GENDER);
		int genderCode = (Integer) genderReader.input();

		writer.prompt(ConstantMsg.MSG_INPUT_BIRTHDAY);
		String birthdayString = (String) birthdayReader.input();

		writer.prompt(ConstantMsg.MSG_INPUT_DEPT_ID);
		int deptId = (Integer) deptReader.input();

		Employee employee = new Employee();
		employee.setEmployeeId(empId);
		employee.setEmployeeName(employeeName);
		employee.setGenderCode(genderCode);
		employee.setBirthdayString(birthdayString);
		employee.setDepartmentId(deptId);

		Integer updateCount = employeeDAO.update(employee);
		if (updateCount != null && updateCount > 0) {
			writer.message(ConstantMsg.MSG_UPDATE_COMPLETE);
		} else {
			writer.message(ConstantMsg.MSG_NOT_FOUND_TARGET);
		}
	}
}
