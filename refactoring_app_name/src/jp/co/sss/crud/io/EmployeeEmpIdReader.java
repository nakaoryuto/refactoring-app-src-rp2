package jp.co.sss.crud.io;

import jp.co.sss.crud.util.ConstantMsg;

public class EmployeeEmpIdReader implements IConsoleReader {

	/** 社員ID入力のエラーメッセージを返す */
	@Override
	public String getErrorMsg() {
		return ConstantMsg.MSG_ERR_EMP_ID;
	}

	/** 入力された社員IDが正しいか判定する（1以上の整数） */
	@Override
	public boolean isValid(String inputString) {
		try {
			int empId = Integer.parseInt(inputString);
			return empId >= 1;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/** 入力値をint変換する必要があるか返す */
	@Override
	public boolean isParseInt() {
		return true;
	}
}
