package jp.co.sss.crud.io;

import java.text.SimpleDateFormat;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

public class EmployeeBirthdayReader implements IConsoleReader {

	/** 生年月日入力のエラーメッセージを返す */
	@Override
	public String getErrorMsg() {
		return ConstantMsg.MSG_ERR_BIRTHDAY;
	}

	/** 入力された生年月日が正しいか判定する（yyyy/MM/dd） */
	@Override
	public boolean isValid(String inputString) {
		if (inputString == null)
			return false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantValue.DATE_PATTERN_YYYYMMDD_SLASH);
			sdf.setLenient(false);
			sdf.parse(inputString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/** 入力値をint変換する必要があるか返す */
	@Override
	public boolean isParseInt() {
		return false;
	}
}
