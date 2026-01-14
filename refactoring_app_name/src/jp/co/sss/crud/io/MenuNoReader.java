package jp.co.sss.crud.io;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

public class MenuNoReader implements IConsoleReader {

	/** メニュー番号入力のエラーメッセージを返す */
	@Override
	public String getErrorMsg() {
		return ConstantMsg.MSG_ERR_MENU_NO;
	}

	/** 入力されたメニュー番号が正しいか判定する */
	@Override
	public boolean isValid(String inputString) {
		try {
			int menuNo = Integer.parseInt(inputString);
			return menuNo >= ConstantValue.MENU_SELECT_ALL && menuNo <= ConstantValue.MENU_EXIT;
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
