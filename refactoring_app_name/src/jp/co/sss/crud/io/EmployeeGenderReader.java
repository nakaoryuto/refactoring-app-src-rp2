package jp.co.sss.crud.io;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

public class EmployeeGenderReader implements IConsoleReader {

	/** 性別入力のエラーメッセージを返す */
	@Override
	public String getErrorMsg() {
		return ConstantMsg.MSG_ERR_GENDER;
	}

	/** 入力された性別コードが正しいか判定する（0/1/2/9のみ） */
	@Override
	public boolean isValid(String inputString) {
		try {
			int genderCode = Integer.parseInt(inputString);
			return genderCode == ConstantValue.GENDER_NO_ANSWER
					|| genderCode == ConstantValue.GENDER_MALE
					|| genderCode == ConstantValue.GENDER_FEMALE
					|| genderCode == ConstantValue.GENDER_OTHER;
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
