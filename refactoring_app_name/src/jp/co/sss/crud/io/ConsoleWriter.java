package jp.co.sss.crud.io;

import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

public class ConsoleWriter {

	/** メニューをコンソールに表示する */
	public void showMenu() {
		System.out.println(ConstantMsg.MSG_SYSTEM_TITLE);
		System.out.println(ConstantMsg.MSG_MENU_1);
		System.out.println(ConstantMsg.MSG_MENU_2);
		System.out.println(ConstantMsg.MSG_MENU_3);
		System.out.println(ConstantMsg.MSG_MENU_4);
		System.out.println(ConstantMsg.MSG_MENU_5);
		System.out.println(ConstantMsg.MSG_MENU_6);
		System.out.println(ConstantMsg.MSG_MENU_7);
		System.out.print(ConstantMsg.MSG_INPUT_MENU_NO);
	}

	/** 入力促進のためのプロンプトを表示する */
	public void prompt(String message) {
		System.out.print(message);
	}

	/** 通常メッセージを表示する */
	public void message(String message) {
		System.out.println(message);
		System.out.println();
	}

	/** エラーメッセージを表示する */
	public void error(String message) {
		System.out.println(message);
		System.out.println();
	}

	/** 社員一覧（DTOリスト）を表示する */
	public void showEmployees(List<Employee> employeeList) {
		if (employeeList == null || employeeList.isEmpty()) {
			System.out.println(ConstantMsg.MSG_NO_RESULT);
			System.out.println();
			return;
		}

		System.out.println(ConstantMsg.MSG_EMPLOYEE_HEADER);
		for (Employee employee : employeeList) {
			System.out.print(employee.getEmployeeId() + "\t");
			System.out.print(employee.getEmployeeName() + "\t");
			System.out.print(toGenderLabel(employee.getGenderCode()) + "\t");
			System.out.print(employee.getBirthdayString() + "\t");
			System.out.println(employee.getDepartmentName());
		}
		System.out.println();
	}

	/** 性別コードを表示用文字列に変換する */
	private String toGenderLabel(Integer genderCode) {
		if (genderCode == null)
			return "";
		if (genderCode == ConstantValue.GENDER_NO_ANSWER)
			return ConstantMsg.MSG_GENDER_NO_ANSWER;
		if (genderCode == ConstantValue.GENDER_MALE)
			return ConstantMsg.MSG_GENDER_MALE;
		if (genderCode == ConstantValue.GENDER_FEMALE)
			return ConstantMsg.MSG_GENDER_FEMALE;
		if (genderCode == ConstantValue.GENDER_OTHER)
			return ConstantMsg.MSG_GENDER_OTHER;
		return "";
	}
}
