package com.niit.jdbc;

import java.util.*;

public class TypeDao extends AbstractDao {
	/**
	 * 获取type_table中所有的类型名
	 * 
	 * @return type_table中所有的类型名
	 */
	public Vector getAll() {
		String types_sql = "select typeName from type_table;";
		Vector typeName = querySinData(types_sql, null);
		return typeName;

	}

	/**
	 * 判断类型是否有记录
	 * 
	 * @param type
	 *            类型名
	 * @return true 有记录，false 没有记录
	 */
	public boolean isVisiable(String type) {
		String sql = "select amount from user_table where type = ?";
		Object[] values = { type };
		Vector v = querySinData(sql, values);
		if (v.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 添加新的类型
	 * 
	 * @param yourType
	 *            类型名
	 */
	public void insert(String yourType) {
		if (yourType != null) {
			String insert_sql = "insert into type_table values(null,?);";
			Object[] values = { yourType };
			dml(insert_sql, values);
		}
	}

	/**
	 * 更改类型
	 * 
	 * @param newType
	 *            新类型名
	 * @param oldType
	 *            旧类型名
	 */
	public void modify(String newType, String oldType) {
		if (newType != null) {
			String mod_sql = "update type_table set typeName = ? where typeName = ?";
			String sql = "update user_table set type = ? where type = ?";
			Object[] values = { newType, oldType };
			dml(mod_sql, values);
			dml(sql, values);
		}
	}

	/**
	 * 删除类型
	 * 
	 * @param type
	 *            类型名
	 */
	public void delete(String type) {
		String sql = "delete from type_table where typeName = ?";
		Object[] values = { type };
		dml(sql, values);
	}

}
