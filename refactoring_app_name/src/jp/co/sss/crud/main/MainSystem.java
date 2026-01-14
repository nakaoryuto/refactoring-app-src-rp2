package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

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
			System.out.println(ConstantMsg.MSG_SYSTEM_TITLE);
			System.out.println(ConstantMsg.MSG_MENU_1);
			System.out.println(ConstantMsg.MSG_MENU_2);
			System.out.println(ConstantMsg.MSG_MENU_3);
			System.out.println(ConstantMsg.MSG_MENU_4);
			System.out.println(ConstantMsg.MSG_MENU_5);
			System.out.println(ConstantMsg.MSG_MENU_6);
			System.out.println(ConstantMsg.MSG_MENU_7);
			System.out.print(ConstantMsg.MSG_INPUT_MENU_NO);

			// メニュー番号の入力
			String selectedMenuNumberString = consoleReader.readLine();
			selectedMenuNumber = Integer.parseInt(selectedMenuNumberString);

			// 機能の呼出
			switch (selectedMenuNumber) {
			case ConstantValue.MENU_ALL_FIND:
				DBController.findAllEmployees();
				break;

			case ConstantValue.MENU_FIND_BY_NAME:
				System.out.print(ConstantMsg.MSG_INPUT_EMP_NAME_SIMPLE);
				DBController.findEmployeesByNameFromConsole();
				break;

			case ConstantValue.MENU_FIND_BY_DEPT:
				System.out.print(ConstantMsg.MSG_INPUT_DEPT_ID_SEARCH);
				String departmentIdString = consoleReader.readLine();
				DBController.findEmployeesByDepartmentId(departmentIdString);
				break;

			case ConstantValue.MENU_INSERT:
				System.out.print(ConstantMsg.MSG_INPUT_EMP_NAME_SIMPLE);
				String employeeName = consoleReader.readLine();

				System.out.print(ConstantMsg.MSG_INPUT_GENDER_SIMPLE);
				String genderCodeString = consoleReader.readLine();

				System.out.print(ConstantMsg.MSG_INPUT_BIRTHDAY_SIMPLE);
				String birthdayString = consoleReader.readLine();

				System.out.print(ConstantMsg.MSG_INPUT_DEPT_ID_SIMPLE);
				String departmentIdStringForInsert = consoleReader.readLine();

				DBController.insertEmployee(employeeName, genderCodeString, birthdayString,
						departmentIdStringForInsert);
				break;

			case ConstantValue.MENU_UPDATE:
				System.out.print(ConstantMsg.MSG_INPUT_UPDATE_EMP_ID);
				String employeeIdStringForUpdate = consoleReader.readLine();

				Integer.parseInt(employeeIdStringForUpdate);

				DBController.updateEmployeeFromConsole(employeeIdStringForUpdate);
				System.out.println(ConstantMsg.MSG_UPDATE_COMPLETE);
				break;

			case ConstantValue.MENU_DELETE:
				System.out.print(ConstantMsg.MSG_INPUT_DELETE_EMP_ID);
				DBController.deleteEmployeeFromConsole();
				break;

			}
		} while (selectedMenuNumber != ConstantValue.MENU_EXIT);

		System.out.println(ConstantMsg.MSG_SYSTEM_EXIT);
	}
}
