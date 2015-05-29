package com.niit.jdbc;

import java.util.*;

public class TypeDao extends AbstractDao {
	/**
	 * ��ȡtype_table�����е�������
	 * 
	 * @return type_table�����е�������
	 */
	public Vector getAll() {
		String types_sql = "select typeName from type_table;";
		Vector typeName = querySinData(types_sql, null);
		return typeName;

	}

	/**
	 * �ж������Ƿ��м�¼
	 * 
	 * @param type
	 *            ������
	 * @return true �м�¼��false û�м�¼
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
	 * ����µ�����
	 * 
	 * @param yourType
	 *            ������
	 */
	public void insert(String yourType) {
		if (yourType != null) {
			String insert_sql = "insert into type_table values(null,?);";
			Object[] values = { yourType };
			dml(insert_sql, values);
		}
	}

	/**
	 * ��������
	 * 
	 * @param newType
	 *            ��������
	 * @param oldType
	 *            ��������
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
	 * ɾ������
	 * 
	 * @param type
	 *            ������
	 */
	public void delete(String type) {
		String sql = "delete from type_table where typeName = ?";
		Object[] values = { type };
		dml(sql, values);
	}

}
