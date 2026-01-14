package jp.co.sss.crud.service;

import java.util.List;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.db.IEmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.io.IConsoleReader;
import jp.co.sss.crud.util.ConstantMsg;

public class EmployeeFindByEmpNameService implements IEmployeeService {

	private final IEmployeeDAO employeeDAO = new EmployeeDAO();
	private final ConsoleWriter writer = new ConsoleWriter();
	private final IConsoleReader employeeNameReader = new EmployeeNameReader();

	/** 社員名を入力して該当社員を検索し一覧表示する */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		writer.prompt(ConstantMsg.MSG_INPUT_EMP_NAME);
		String searchName = (String) employeeNameReader.input();

		List<Employee> employeeList = employeeDAO.findByEmployeeName(searchName);
		writer.showEmployees(employeeList);
	}
}
