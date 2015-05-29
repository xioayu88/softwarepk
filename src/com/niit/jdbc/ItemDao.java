package com.niit.jdbc;

import java.util.*;

public class ItemDao extends AbstractDao {
	/**
	 * 创建一条记录
	 * 
	 * @param name
	 *            用户名
	 * @param pass
	 *            用户密码
	 * @param amount
	 *            金额数量
	 * @param date
	 *            日期
	 * @param type
	 *            类型
	 * @param desc
	 *            描述
	 * @param remark
	 *            备注
	 */
	public int add(String name, String pass, int amount, String date,
			String type, String desc, String remark) {
		String sql = "insert into user_table values(null,?,?,?,?,?,?,?);";
		Object[] values = { name, pass, amount, date, type, desc, remark };
		int affect = dml(sql, values);
		return affect;
	}

	/**
	 * 根据id在user_table中修改记录
	 * 
	 * @param date
	 *            日期
	 * @param description
	 *            类型
	 * @param amount
	 *            数量
	 * @param type
	 *            类型
	 * @param remark
	 *            备注
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
	 * 根据id删除一条记录
	 * 
	 * @param id
	 */
	public void delete(int id) {
		String sql = "delete from user_table where id = ?";
		Object[] values = { id };
		dml(sql, values);
	}

	/**
	 * 根据id返回一条记录
	 * 
	 * @param id
	 * @return 查询的结果集合
	 */
	public Vector query(int id) {
		String sql = "select date,description,amount,type,remark from user_table where id = ?";
		Object[] values = { id };
		Vector v = queryRowData(sql, values);
		return v;
	}

	/**
	 * 根据日期和用户名选择id
	 * 
	 * @param d1
	 *            日期
	 * @param user
	 *            用户名
	 * @return id集合
	 */
	public Vector query_id(String d1, String user) {
		String sql = "select id from user_table where date like ? and name = ?";
		Object[] values = { d1, user };
		Vector v = queryStrData(sql, values);
		return v;
	}

	/**
	 * 根据日期和用户名选择
	 * 
	 * @param d1
	 *            日期
	 * @param user
	 *            用户名
	 * @return 选择的结果集合
	 */

	public Vector[] query_data(String d1, String user) {
		String sql = "select * from user_table where date like ? and name = ?";
		Object[] values = { d1, user };
		Vector[] v = queryTabData(sql, values);
		return v;
	}

	/**
	 * 无限制选择
	 * 
	 * @return 选择的结果集合
	 */
	public Vector[] query_pub() {
		String sql = "select * from user_table";
		Vector v[] = queryTabData(sql, null);
		return v;
	}

}
