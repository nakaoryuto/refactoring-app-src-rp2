package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void findAllEmployees() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			// resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.MSG_NO_RESULT);
				return;
			}

			// レコードを出力
			System.out.println(ConstantMsg.MSG_EMPLOYEE_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id") + "\t");
				System.out.print(resultSet.getString("emp_name") + "\t");

				int genderCode = Integer.parseInt(resultSet.getString("gender"));
				if (genderCode == ConstantValue.GENDER_NO_ANSWER) {
					System.out.print(ConstantMsg.MSG_GENDER_NO_ANSWER + "\t");
				} else if (genderCode == ConstantValue.GENDER_MALE) {
					System.out.print(ConstantMsg.MSG_GENDER_MALE + "\t");

				} else if (genderCode == ConstantValue.GENDER_FEMALE) {
					System.out.print(ConstantMsg.MSG_GENDER_FEMALE + "\t");

				} else if (genderCode == ConstantValue.GENDER_OTHER) {
					System.out.print(ConstantMsg.MSG_GENDER_OTHER + "\t");

				}

				System.out.print(resultSet.getString("birthday") + "\t");
				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * コンソール入力された社員名で社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByNameFromConsole() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		// 検索キーワード（社員名）
		String employeeNameKeyword = consoleReader.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, "%" + employeeNameKeyword + "%");

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.MSG_NO_RESULT);
				return;
			}

			System.out.println(ConstantMsg.MSG_EMPLOYEE_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderCodeString = resultSet.getString("gender");
				int genderCode = Integer.parseInt(genderCodeString);
				if (genderCode == ConstantValue.GENDER_NO_ANSWER) {
					System.out.print(ConstantMsg.MSG_GENDER_NO_ANSWER);
				} else if (genderCode == ConstantValue.GENDER_MALE) {
					System.out.print(ConstantMsg.MSG_GENDER_MALE);

				} else if (genderCode == ConstantValue.GENDER_FEMALE) {
					System.out.print(ConstantMsg.MSG_GENDER_FEMALE);

				} else if (genderCode == ConstantValue.GENDER_OTHER) {
					System.out.print(ConstantMsg.MSG_GENDER_OTHER);

				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @param departmentIdString 部署ID（文字列）
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByDepartmentId(String departmentIdString)
			throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(1, Integer.parseInt(departmentIdString));

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.MSG_NO_RESULT);
				return;
			}

			System.out.println(ConstantMsg.MSG_EMPLOYEE_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderCodeString = resultSet.getString("gender");
				int genderCode = Integer.parseInt(genderCodeString);
				if (genderCode == ConstantValue.GENDER_NO_ANSWER) {
					System.out.print(ConstantMsg.MSG_GENDER_NO_ANSWER);
				} else if (genderCode == ConstantValue.GENDER_MALE) {
					System.out.print(ConstantMsg.MSG_GENDER_MALE);

				} else if (genderCode == ConstantValue.GENDER_FEMALE) {
					System.out.print(ConstantMsg.MSG_GENDER_FEMALE);

				} else if (genderCode == ConstantValue.GENDER_OTHER) {
					System.out.print(ConstantMsg.MSG_GENDER_OTHER);

				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				String departmentIdInResultString = resultSet.getString("dept_id");
				int departmentIdInResult = Integer.parseInt(departmentIdInResultString);
				if (departmentIdInResult == ConstantValue.DEPT_SALES) {
					System.out.println(ConstantMsg.MSG_DEPT_SALES);
				} else if (departmentIdInResult == ConstantValue.DEPT_ACCOUNTING) {
					System.out.println(ConstantMsg.MSG_DEPT_ACCOUNTING);
				} else if (genderCode == ConstantValue.DEPT_GENERAL_AFFAIRS) {
					// ※元コードの条件(genderCode == 3)の挙動を変えないため、そのまま定数化のみ実施
					System.out.println(ConstantMsg.MSG_DEPT_GENERAL_AFFAIRS);

				}
			}

			System.out.println("");
		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param employeeName       社員名
	 * @param genderCodeString   性別（文字列）
	 * @param birthdayString     生年月日（文字列）
	 * @param departmentIdString 部署ID（文字列）
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 * @throws ParseException
	 */
	public static void insertEmployee(String employeeName, String genderCodeString, String birthdayString,
			String departmentIdString) throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, employeeName);
			preparedStatement.setInt(2, Integer.parseInt(genderCodeString));
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantValue.DATE_PATTERN_YYYYMMDD_SLASH);
			preparedStatement.setObject(3, sdf.parse(birthdayString), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(departmentIdString));

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println(ConstantMsg.MSG_INSERT_COMPLETE);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新（更新内容はコンソール入力）
	 * 
	 * @param employeeIdString 社員ID（文字列）
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 * @throws ParseException
	 */
	public static void updateEmployeeFromConsole(String employeeIdString)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(ConstantMsg.MSG_INPUT_EMP_NAME_COLON);
			String employeeName = consoleReader.readLine();

			// 性別を入力
			System.out.print(ConstantMsg.MSG_INPUT_GENDER_COLON);
			String genderCodeString = consoleReader.readLine();

			// 誕生日を入力
			System.out.print(ConstantMsg.MSG_INPUT_BIRTHDAY_COLON);
			String birthdayString = consoleReader.readLine();

			// 部署IDを入力
			System.out.print(ConstantMsg.MSG_INPUT_DEPT_ID_COLON);
			String departmentIdString = consoleReader.readLine();

			// 入力値をバインド
			preparedStatement.setString(1, employeeName);
			preparedStatement.setInt(2, Integer.parseInt(genderCodeString));
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantValue.DATE_PATTERN_YYYYMMDD_SLASH);
			preparedStatement.setObject(3, sdf.parse(birthdayString), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(departmentIdString));
			preparedStatement.setInt(5, Integer.parseInt(employeeIdString));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// クローズ処理
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除（社員IDはコンソール入力）
	 */
	public static void deleteEmployeeFromConsole() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			String employeeIdString = consoleReader.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員IDをバインド
			preparedStatement.setInt(1, Integer.parseInt(employeeIdString));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println(ConstantMsg.MSG_DELETE_COMPLETE);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// Statementをクローズ
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
