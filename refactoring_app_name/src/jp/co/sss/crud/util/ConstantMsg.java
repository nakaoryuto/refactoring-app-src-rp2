package jp.co.sss.crud.util;

/**
 * 画面表示メッセージ定数クラス(2:マジックナンバー、メッセージの定数化)
 */
public class ConstantMsg {

	/** インスタンス化を禁止 */
	private ConstantMsg() {
	}

	// ----- 共通メッセージ -----
	public static final String MSG_NO_RESULT = "該当者はいませんでした";

	// ----- 表示ヘッダー -----
	public static final String MSG_EMPLOYEE_HEADER = "社員ID\t社員名\t性別\t生年月日\t部署名";

	// ----- 性別表示 -----
	public static final String MSG_GENDER_NO_ANSWER = "回答なし";
	public static final String MSG_GENDER_MALE = "男性";
	public static final String MSG_GENDER_FEMALE = "女性";
	public static final String MSG_GENDER_OTHER = "その他";

	// ----- 部署表示（findEmployeesByDepartmentId 内で使用） -----
	public static final String MSG_DEPT_SALES = "営業部";
	public static final String MSG_DEPT_ACCOUNTING = "経理部";
	public static final String MSG_DEPT_GENERAL_AFFAIRS = "総務部";

	// ----- 完了メッセージ -----
	public static final String MSG_INSERT_COMPLETE = "社員情報を登録しました";
	public static final String MSG_UPDATE_COMPLETE = "社員情報を更新しました";
	public static final String MSG_DELETE_COMPLETE = "社員情報を削除しました";

	// ----- メニュー表示 -----
	public static final String MSG_SYSTEM_TITLE = "=== 社員管理システム ===";
	public static final String MSG_MENU_1 = "1.全件表示";
	public static final String MSG_MENU_2 = "2.社員名検索";
	public static final String MSG_MENU_3 = "3.部署ID検索";
	public static final String MSG_MENU_4 = "4.新規登録";
	public static final String MSG_MENU_5 = "5.更新";
	public static final String MSG_MENU_6 = "6.削除";
	public static final String MSG_MENU_7 = "7.終了";
	public static final String MSG_INPUT_MENU_NO = "メニュー番号を入力してください：";

	// ----- 入力（MainSystem側） -----
	public static final String MSG_INPUT_EMP_NAME_SIMPLE = "社員名:";
	public static final String MSG_INPUT_GENDER_SIMPLE = "性別(0:その他, 1:男性, 2:女性, 9:回答なし):";
	public static final String MSG_INPUT_BIRTHDAY_SIMPLE = "生年月日(西暦年/月/日):";
	public static final String MSG_INPUT_DEPT_ID_SIMPLE = "部署ID(1:営業部、2:経理部、3:総務部):";
	public static final String MSG_INPUT_DEPT_ID_SEARCH = "部署ID(1:営業部、2:経理部、3:総務部)を入力してください:";
	public static final String MSG_INPUT_UPDATE_EMP_ID = "更新する社員の社員IDを入力してください：";
	public static final String MSG_INPUT_DELETE_EMP_ID = "削除する社員の社員IDを入力してください：";

	// ----- 入力（DBController update側：現状の文字列をそのまま定数化） -----
	public static final String MSG_INPUT_EMP_NAME_COLON = "社員名：";
	public static final String MSG_INPUT_GENDER_COLON = "性別(0:回答しない, 1:男性, 2:女性, 9:その他):";
	public static final String MSG_INPUT_BIRTHDAY_COLON = "生年月日(西暦年/月/日)：";
	public static final String MSG_INPUT_DEPT_ID_COLON = "部署ID(1：営業部、2：経理部、3：総務部)：";

	// ----- 終了メッセージ -----
	public static final String MSG_SYSTEM_EXIT = "システムを終了します。";
}
