package jp.co.sss.crud.service;

import java.util.List;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.db.IEmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.IConsoleReader;
import jp.co.sss.crud.util.ConstantMsg;

public class EmployeeFindByDeptIdService implements IEmployeeService {

	private final IEmployeeDAO employeeDAO = new EmployeeDAO();
	private final ConsoleWriter writer = new ConsoleWriter();
	private final IConsoleReader deptIdReader = new EmployeeDeptIdReader();

	/** 部署IDを入力して該当社員を検索し一覧表示する */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		writer.prompt(ConstantMsg.MSG_INPUT_DEPT_ID);
		int deptId = (Integer) deptIdReader.input();

		List<Employee> employeeList = employeeDAO.findByDeptId(deptId);
		writer.showEmployees(employeeList);
	}
}
