package jp.co.sss.crud.io;

import jp.co.sss.crud.util.ConstantMsg;

public class EmployeeNameReader implements IConsoleReader {

	/** 社員名入力のエラーメッセージを返す */
	@Override
	public String getErrorMsg() {
		return ConstantMsg.MSG_ERR_EMP_NAME;
	}

	/** 入力された社員名が正しいか判定する（空文字を禁止） */
	@Override
	public boolean isValid(String inputString) {
		return inputString != null && !inputString.trim().isEmpty();
	}

	/** 入力値をint変換する必要があるか返す */
	@Override
	public boolean isParseInt() {
		return false;
	}
}
