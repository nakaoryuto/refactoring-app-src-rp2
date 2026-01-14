package jp.co.sss.crud.db;

import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.SystemErrorException;

public interface IEmployeeDAO {
	/**
	 * 全件表示
	 *
	 * @return {@code List<Employee>} 全社員エンティティリスト
	 * @throws SystemErrorException : {@code ClassNotFoundException | SQLException }をキャッチしてスローする
	 */
	List<Employee> findAll() throws SystemErrorException;

	/**
	 * 社員名検索
	 * 
	 * @param searchName 検索社員名 
	 * @return {@code List<Employee>} 検索社員名を含むエンティティリスト
	 * @throws SystemErrorException : {@code ClassNotFoundException | SQLException }をキャッチしてスローする
	 */
	List<Employee> findByEmployeeName(String searchName) throws SystemErrorException;

	/**
	 * 部署ID検索
	 * 
	 * @param deptId 部署ID
	 * @return {@code List<Employee>} 検索部署IDを含むエンティティリスト
	 * @throws SystemErrorException : {@code ClassNotFoundException | SQLException }をキャッチしてスローする
	 */
	List<Employee> findByDeptId(int deptId) throws SystemErrorException;

	/**
	 * 登録
	 * <br>引数のEmployeeから社員名、性別、生年月日、部署番号を取得し新たな社員情報を生成する。
	 * <br>社員IDは自動採番機能を用いること
	 * 
	 * @param employee
	 * @throws SystemErrorException : {@code ClassNotFoundException | SQLException }をキャッチしてスローする
	 */
	void insert(Employee employee) throws SystemErrorException;

	/**
	 * 社員情報を1件更新する
	 * <br>引数のEmployeeから社員ID、社員名、性別、生年月日、部署番号を取得し社員情報を更新する。
	 * 
	 * @param employee
	 * @throws SystemErrorException : {@code ClassNotFoundException | SQLException }をキャッチしてスローする
	 */
	Integer update(Employee employee) throws SystemErrorException;

	/**
	 * 社員情報を1件削除する
	 * <br>引数のEmployeeから社員IDから社員情報を削除する。
	 * 
	 * @param empId 
	 * @throws SystemErrorException : {@code ClassNotFoundException | SQLException }をキャッチしてスローする
	 */
	Integer delete(Integer empId) throws SystemErrorException;

}
