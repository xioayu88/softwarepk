package com.niit.jdbc;

import java.util.*;

public class UserDao extends AbstractDao {
	/**
	 * 登陆时检查用户名和密码是否在用户表中
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @return 若是true，则此账户在表中
	 */
	public boolean loginCheck_NAP(String username, String password) {
		String user_check = "select * from NAP_table "
				+ " where name = ? and password = ? ";
		Object[] values = { username, password };
		Vector data = queryMulData(user_check, values);
		if (data.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public boolean Check_NAP(String password) {
		String user_check = "select * from NAP_table where password = ? ";
		Object[] values = { password };
		Vector data = queryMulData(user_check, values);
		if (data.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public void insert_NAP(String name, String pass, String oldpass) {
		String namesql = "update NAP_table set name = ?,password = ? where password = ?";
		Object[] values = { name, pass, oldpass };
		dml(namesql, values);
	}

	/**
	 * 登陆时检查用户名和密码是否在用户表中
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @return 若是true，则此账户在表中
	 */
	public boolean loginCheck_admin(String username, String password) {
		String user_check = "select * from admin_table "
				+ " where name = ? and password = ? ";
		Object[] values = { username, password };
		Vector data = queryMulData(user_check, values);
		if (data.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 登陆时检查用户名和密码是否在禁用账户表中
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @return 若是true，则此账户在表中
	 */
	public boolean loginCheck_stop(String username) {
		String stop_check = "select * from stop_table " + " where name = ? ";
		Object[] values = { username };
		Vector data = queryMulData(stop_check, values);
		if (data.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 检查用户是否在admin_table中
	 * 
	 * @param name
	 *            用户名
	 * @return true 在admin_table中
	 */
	public boolean addUserCheck(String name) {
		String user_check = "select name from admin_table ";
		Vector data = querySinData(user_check, null);
		if (data.contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 向admin_table中插入用户名和密码
	 * 
	 * @param name
	 *            用户名
	 * @param pass
	 *            密码
	 * @return 插入的行数
	 */
	public int addUser(String name, String pass) {
		int affect = 0;
		String add_admin = "insert into admin_table values(null,?,?)";
		Object[] values = { name, pass };
		affect = dml(add_admin, values);
		return affect;
	}

	/**
	 * 检查
	 * 
	 * @param username
	 * @return
	 */
	public boolean nameCheck_admin(String username) {
		String user_check = "select * from admin_table " + " where name = ?";
		Object[] values = { username };
		Vector data = queryMulData(user_check, values);
		if (data.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 将禁用用户名插入到禁用表中
	 * 
	 * @param username
	 *            用户名
	 * @return 插入的行数
	 */
	public int nameInsert_stop(String username) {
		int affect = 0;
		String add_stop = "insert into stop_table values(null,?);";
		Object[] values = { username };
		affect = dml(add_stop, values);
		return affect;
	}

	/**
	 * 将用户名从禁用表中删除
	 * 
	 * @param username
	 *            用户名
	 */
	public void nameDelete_stop(String username) {
		String del_stop = "delete from stop_table where name = ?";
		Object[] values = { username };
		dml(del_stop, values);
	}

	/**
	 * 根据用户名从use_table表中查询列名
	 * 
	 * @param username
	 *            用户名
	 * @return 查询的结果集合
	 */
	public Vector[] Query_user(String username) {
		String column = "select name,password from admin_table where name like ?";
		Object[] values = { username };
		Vector[] tab = new Vector[2];
		tab = queryTabData(column, values);
		return tab;
	}

}
