package jp.co.sss.crud.util;

/**
 * 数値等の定数をまとめたクラス
 */
public class ConstantValue {

	private ConstantValue() {
	}

	// ----- メニュー番号（IEmployeeServiceで参照する名前） -----
	public static final int MENU_SELECT_ALL = 1;
	public static final int MENU_SEARCH_EMP_NAME = 2;
	public static final int MENU_SEARCH_DEPT_ID = 3;
	public static final int MENU_INSERT = 4;
	public static final int MENU_UPDATE = 5;
	public static final int MENU_DELETE = 6;
	public static final int MENU_EXIT = 7;

	// ===== 互換用（MainSystemが参照している定数名） =====
	public static final int MENU_ALL_FIND = MENU_SELECT_ALL;
	public static final int MENU_FIND_BY_NAME = MENU_SEARCH_EMP_NAME;
	public static final int MENU_FIND_BY_DEPT = MENU_SEARCH_DEPT_ID;

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
