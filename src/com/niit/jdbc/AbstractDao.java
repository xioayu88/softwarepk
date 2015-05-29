package com.niit.jdbc;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * �����Dao��
 * 
 * @author Administrator
 * 
 */

public abstract class AbstractDao {
	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ResultSetMetaData metaData;

	public AbstractDao() {
		try {
			// ʹ��Properties�������������ļ�
			Properties property = new Properties();
			File file = new File("mySQL/mySQL.properties");
			property.load(new FileInputStream(file));
			String driver = property.getProperty("driver");
			String url = property.getProperty("url");
			String user = property.getProperty("user");
			String password = property.getProperty("password");
			// ��������
			Class.forName(driver);
			// ��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Vector[] queryTabData(String sql, Object[] values) {
		Vector[] tab = new Vector[2];
		Vector names = new Vector();
		Vector data = new Vector();
		try {
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}

			}
			rs = pstmt.executeQuery();
			metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				names.add(metaData.getColumnName(i + 1));
			}
			while (rs.next()) {
				Vector v = new Vector();
				for (int i = 0; i < columnCount; i++) {
					v.add(rs.getString(i + 1));
				}
				data.add(v);
			}
			tab[0] = names;
			tab[1] = data;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tab;

	}

	/**
	 * ��ѯ��������,�ڴ˻����ϼ���һ�κ���
	 * 
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param values
	 *            Ҫ�󶨵Ĳ����������޲������򴫵�null
	 * @return �������
	 */
	protected Vector queryStrData(String sql, Object[] values) {
		Vector data = new Vector();
		try {
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}

			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String number = "��¼�ı���ǣ�" + id;
				data.add(number);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;

	}

	/**
	 * ��ѯ��������
	 * 
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param values
	 *            Ҫ�󶨵Ĳ����������޲������򴫵�null
	 * @return �������
	 */
	protected Vector querySinData(String sql, Object[] values) {
		Vector data = new Vector();
		try {
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}

			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				data.add(rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;

	}

	/**
	 * ��ѯ���ж�������
	 * 
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param values
	 *            Ҫ�󶨵Ĳ������飬���޲������򴫵�null
	 * @return �������
	 */
	protected Vector<Vector> queryMulData(String sql, Object[] values) {
		Vector data = new Vector();
		Vector v = new Vector();
		try {
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}

			}
			rs = pstmt.executeQuery();
			metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < columnCount; i++) {
					v.add(rs.getObject(i + 1));
				}
				data.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param values
	 *            Ҫ�󶨵Ĳ������飬���޲������򴫵�null
	 * @return �������
	 */
	public Vector queryRowData(String sql, Object[] values) {
		Vector data = new Vector();
		try {
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}

			}
			rs = pstmt.executeQuery();
			metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < columnCount; i++) {
					data.add(rs.getObject(i + 1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;

	}

	/**
	 * ִ��dml���
	 * 
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param values
	 *            �󶨵Ĳ������飬���޲�������Ϊnull
	 * @return ִ�к������
	 */
	protected int dml(String sql, Object[] values) {
		int affect = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}

			}
			affect = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return affect;

	}

}
