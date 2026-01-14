package jp.co.sss.crud.service;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.db.IEmployeeDAO;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeEmpIdReader;
import jp.co.sss.crud.io.IConsoleReader;
import jp.co.sss.crud.util.ConstantMsg;

public class EmployeeDeleteService implements IEmployeeService {

	private final IEmployeeDAO employeeDAO = new EmployeeDAO();
	private final ConsoleWriter writer = new ConsoleWriter();
	private final IConsoleReader empIdReader = new EmployeeEmpIdReader();

	/** 社員IDを指定して社員情報を削除する */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		writer.prompt(ConstantMsg.MSG_INPUT_DELETE_EMP_ID);
		int empId = (Integer) empIdReader.input();

		Integer deleteCount = employeeDAO.delete(empId);
		if (deleteCount != null && deleteCount > 0) {
			writer.message(ConstantMsg.MSG_DELETE_COMPLETE);
		} else {
			writer.message(ConstantMsg.MSG_NOT_FOUND_TARGET);
		}
	}
}
