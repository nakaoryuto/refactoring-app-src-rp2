package jp.co.sss.crud.exception;

/**
 * 入力値の不正例外
 */
public class IllegalInputException extends Exception {

	public IllegalInputException(String message) {
		super(message);
	}
}
