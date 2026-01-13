package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		int selectedMenuNumber = 0;

		do {
			// メニューの表示
			System.out.println("=== 社員管理システム ===");
			System.out.println("1.全件表示");
			System.out.println("2.社員名検索");
			System.out.println("3.部署ID検索");
			System.out.println("4.新規登録");
			System.out.println("5.更新");
			System.out.println("6.削除");
			System.out.println("7.終了");
			System.out.print("メニュー番号を入力してください：");

			// メニュー番号の入力
			String selectedMenuNumberString = consoleReader.readLine();
			selectedMenuNumber = Integer.parseInt(selectedMenuNumberString);

			// 機能の呼出
			switch (selectedMenuNumber) {
			case 1:
				DBController.findAllEmployees();
				break;

			case 2:
				System.out.print("社員名:");
				DBController.findEmployeesByNameFromConsole();
				break;

			case 3:
				System.out.print("部署ID(1:営業部、2:経理部、3:総務部)を入力してください:");
				String departmentIdString = consoleReader.readLine();
				DBController.findEmployeesByDepartmentId(departmentIdString);
				break;

			case 4:
				System.out.print("社員名:");
				String employeeName = consoleReader.readLine();

				System.out.print("性別(0:その他, 1:男性, 2:女性, 9:回答なし):");
				String genderCodeString = consoleReader.readLine();

				System.out.print("生年月日(西暦年/月/日):");
				String birthdayString = consoleReader.readLine();

				System.out.print("部署ID(1:営業部、2:経理部、3:総務部):");
				String departmentIdStringForInsert = consoleReader.readLine();

				DBController.insertEmployee(employeeName, genderCodeString, birthdayString,
						departmentIdStringForInsert);
				break;

			case 5:
				System.out.print("更新する社員の社員IDを入力してください：");
				String employeeIdStringForUpdate = consoleReader.readLine();

				Integer.parseInt(employeeIdStringForUpdate);

				DBController.updateEmployeeFromConsole(employeeIdStringForUpdate);
				System.out.println("社員情報を更新しました");
				break;

			case 6:
				System.out.print("削除する社員の社員IDを入力してください：");
				DBController.deleteEmployeeFromConsole();
				break;

			}
		} while (selectedMenuNumber != 7);

		System.out.println("システムを終了します。");
	}
}
