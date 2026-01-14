package jp.co.sss.crud.io;

import static jp.co.sss.crud.util.ConstantMsg.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;

/**
 * コンソール入力・入力チェック機能のインターフェース
 */

public interface IConsoleReader {

	/**
	 * コンソールから読み取るためのメソッド
	 * <br>呼び出し先で戻り値を適正な型（IntegerやString）にキャストすること
	 * <br>またisValidメソッドを呼び出し、入力値のチェックを行う。
	 * 
	 * @return 入力値
	 * @throws SystemErrorException IOExceptionをキャッチして送出する
	 * @throws IllegalInputException isValidメソッドの結果がfalseの場合に送出する
	 */
	public default Object input() throws SystemErrorException, IllegalInputException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			//			br.close();//IOExceptionを発生させる場合はコメントを外して実行する
			input = br.readLine();
			if (!isValid(input)) {
				String errorMsg = getErrorMsg();
				throw new IllegalInputException(errorMsg);
			}
		} catch (IOException e) {
			throw new SystemErrorException(MSG_SYSTEM_ERROR, e);
		}

		if (isParseInt()) {
			return Integer.parseInt(input);
		}

		return input;

	}

	/**
	 * エラーメッセージを返すメソッド
	 * 
	 * @return 入力条件のエラーメッセージ
	 * ex "4文字以上8文字以内の半角数字を入力してください"
	 */
	String getErrorMsg();

	/**
	 * 入力文字列のバリデーションメソッド
	 * 
	 * @param inputString コンソール入力した文字列
	 * @return inputStringが適正な値であるときはtrue、そうでないときはfalseを返す。
	 */
	boolean isValid(String inputString);

	/**
	 * コンソール入力した文字列をintに変換するかどうかを返す。
	 * 
	 * @return inputをint型へ変換する必要があるときはtrue、そうでないときはfalseを返す。
	 */
	boolean isParseInt();

}
