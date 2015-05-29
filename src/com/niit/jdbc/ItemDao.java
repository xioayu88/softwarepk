package com.niit.jdbc;

import java.util.*;

public class ItemDao extends AbstractDao {
	/**
	 * ����һ����¼
	 * 
	 * @param name
	 *            �û���
	 * @param pass
	 *            �û�����
	 * @param amount
	 *            �������
	 * @param date
	 *            ����
	 * @param type
	 *            ����
	 * @param desc
	 *            ����
	 * @param remark
	 *            ��ע
	 */
	public int add(String name, String pass, int amount, String date,
			String type, String desc, String remark) {
		String sql = "insert into user_table values(null,?,?,?,?,?,?,?);";
		Object[] values = { name, pass, amount, date, type, desc, remark };
		int affect = dml(sql, values);
		return affect;
	}

	/**
	 * ����id��user_table���޸ļ�¼
	 * 
	 * @param date
	 *            ����
	 * @param description
	 *            ����
	 * @param amount
	 *            ����
	 * @param type
	 *            ����
	 * @param remark
	 *            ��ע
	 * @param id
	 */
	public int modify(String date, String description, int amount, String type,
			String remark, int id) {
		int affect = 0;
		String sql = "update user_table set date = ?,description = ?,"
				+ "amount = ?,type = ? ,remark = ? where id = ?;";
		Object[] values = { date, description, amount, type, remark, id };
		affect = dml(sql, values);
		return affect;
	}

	/**
	 * ����idɾ��һ����¼
	 * 
	 * @param id
	 */
	public void delete(int id) {
		String sql = "delete from user_table where id = ?";
		Object[] values = { id };
		dml(sql, values);
	}

	/**
	 * ����id����һ����¼
	 * 
	 * @param id
	 * @return ��ѯ�Ľ������
	 */
	public Vector query(int id) {
		String sql = "select date,description,amount,type,remark from user_table where id = ?";
		Object[] values = { id };
		Vector v = queryRowData(sql, values);
		return v;
	}

	/**
	 * �������ں��û���ѡ��id
	 * 
	 * @param d1
	 *            ����
	 * @param user
	 *            �û���
	 * @return id����
	 */
	public Vector query_id(String d1, String user) {
		String sql = "select id from user_table where date like ? and name = ?";
		Object[] values = { d1, user };
		Vector v = queryStrData(sql, values);
		return v;
	}

	/**
	 * �������ں��û���ѡ��
	 * 
	 * @param d1
	 *            ����
	 * @param user
	 *            �û���
	 * @return ѡ��Ľ������
	 */

	public Vector[] query_data(String d1, String user) {
		String sql = "select * from user_table where date like ? and name = ?";
		Object[] values = { d1, user };
		Vector[] v = queryTabData(sql, values);
		return v;
	}

	/**
	 * ������ѡ��
	 * 
	 * @return ѡ��Ľ������
	 */
	public Vector[] query_pub() {
		String sql = "select * from user_table";
		Vector v[] = queryTabData(sql, null);
		return v;
	}

}
