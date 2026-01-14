package jp.co.sss.crud.exception;

/**
 * DB/IOなどシステム系の例外
 */
public class SystemErrorException extends Exception {

	public SystemErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
