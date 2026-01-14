package jp.co.sss.crud.util;

/**
 * 数値などの定数をまとめたクラス(2:マジックナンバー、メッセージの定数化)
 */
public class ConstantValue {

	/** インスタンス化を禁止 */
	private ConstantValue() {
	}

	// ----- メニュー番号 -----
	public static final int MENU_ALL_FIND = 1;
	public static final int MENU_FIND_BY_NAME = 2;
	public static final int MENU_FIND_BY_DEPT = 3;
	public static final int MENU_INSERT = 4;
	public static final int MENU_UPDATE = 5;
	public static final int MENU_DELETE = 6;
	public static final int MENU_EXIT = 7;

	// ----- 性別コード -----
	public static final int GENDER_NO_ANSWER = 0;
	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;
	public static final int GENDER_OTHER = 9;

	// ----- 部署ID -----
	public static final int DEPT_SALES = 1;
	public static final int DEPT_ACCOUNTING = 2;
	public static final int DEPT_GENERAL_AFFAIRS = 3;

	// ----- 日付フォーマット -----
	public static final String DATE_PATTERN_YYYYMMDD_SLASH = "yyyy/MM/dd";
}
