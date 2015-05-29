package com.niit.jdbc;

import java.util.*;

public class UserDao extends AbstractDao {
	/**
	 * ��½ʱ����û����������Ƿ����û�����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @return ����true������˻��ڱ���
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
	 * ��½ʱ����û����������Ƿ����û�����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @return ����true������˻��ڱ���
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
	 * ��½ʱ����û����������Ƿ��ڽ����˻�����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @return ����true������˻��ڱ���
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
	 * ����û��Ƿ���admin_table��
	 * 
	 * @param name
	 *            �û���
	 * @return true ��admin_table��
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
	 * ��admin_table�в����û���������
	 * 
	 * @param name
	 *            �û���
	 * @param pass
	 *            ����
	 * @return ���������
	 */
	public int addUser(String name, String pass) {
		int affect = 0;
		String add_admin = "insert into admin_table values(null,?,?)";
		Object[] values = { name, pass };
		affect = dml(add_admin, values);
		return affect;
	}

	/**
	 * ���
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
	 * �������û������뵽���ñ���
	 * 
	 * @param username
	 *            �û���
	 * @return ���������
	 */
	public int nameInsert_stop(String username) {
		int affect = 0;
		String add_stop = "insert into stop_table values(null,?);";
		Object[] values = { username };
		affect = dml(add_stop, values);
		return affect;
	}

	/**
	 * ���û����ӽ��ñ���ɾ��
	 * 
	 * @param username
	 *            �û���
	 */
	public void nameDelete_stop(String username) {
		String del_stop = "delete from stop_table where name = ?";
		Object[] values = { username };
		dml(del_stop, values);
	}

	/**
	 * �����û�����use_table���в�ѯ����
	 * 
	 * @param username
	 *            �û���
	 * @return ��ѯ�Ľ������
	 */
	public Vector[] Query_user(String username) {
		String column = "select name,password from admin_table where name like ?";
		Object[] values = { username };
		Vector[] tab = new Vector[2];
		tab = queryTabData(column, values);
		return tab;
	}

}
