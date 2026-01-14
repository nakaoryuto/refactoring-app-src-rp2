package jp.co.sss.crud.io;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

public class EmployeeDeptIdReader implements IConsoleReader {

	/** 部署ID入力のエラーメッセージを返す */
	@Override
	public String getErrorMsg() {
		return ConstantMsg.MSG_ERR_DEPT_ID;
	}

	/** 入力された部署IDが正しいか判定する（1/2/3のみ） */
	@Override
	public boolean isValid(String inputString) {
		try {
			int deptId = Integer.parseInt(inputString);
			return deptId == ConstantValue.DEPT_SALES
					|| deptId == ConstantValue.DEPT_ACCOUNTING
					|| deptId == ConstantValue.DEPT_GENERAL_AFFAIRS;
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
