package jp.co.sss.crud.service;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.db.IEmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeBirthdayReader;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.EmployeeGenderReader;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.io.IConsoleReader;
import jp.co.sss.crud.util.ConstantMsg;

public class EmployeeRegisterService implements IEmployeeService {

	private final IEmployeeDAO employeeDAO = new EmployeeDAO();
	private final ConsoleWriter writer = new ConsoleWriter();

	private final IConsoleReader nameReader = new EmployeeNameReader();
	private final IConsoleReader genderReader = new EmployeeGenderReader();
	private final IConsoleReader birthdayReader = new EmployeeBirthdayReader();
	private final IConsoleReader deptReader = new EmployeeDeptIdReader();

	/** 社員情報を入力して新規登録する */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		writer.prompt(ConstantMsg.MSG_INPUT_EMP_NAME);
		String employeeName = (String) nameReader.input();

		writer.prompt(ConstantMsg.MSG_INPUT_GENDER);
		int genderCode = (Integer) genderReader.input();

		writer.prompt(ConstantMsg.MSG_INPUT_BIRTHDAY);
		String birthdayString = (String) birthdayReader.input();

		writer.prompt(ConstantMsg.MSG_INPUT_DEPT_ID);
		int deptId = (Integer) deptReader.input();

		Employee employee = new Employee();
		employee.setEmployeeName(employeeName);
		employee.setGenderCode(genderCode);
		employee.setBirthdayString(birthdayString);
		employee.setDepartmentId(deptId);

		employeeDAO.insert(employee);
		writer.message(ConstantMsg.MSG_INSERT_COMPLETE);
	}
}
