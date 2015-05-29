package com.niit.jdbc;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * 抽象的Dao类
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
			// 使用Properties类来加载属性文件
			Properties property = new Properties();
			File file = new File("mySQL/mySQL.properties");
			property.load(new FileInputStream(file));
			String driver = property.getProperty("driver");
			String url = property.getProperty("url");
			String user = property.getProperty("user");
			String password = property.getProperty("password");
			// 加载驱动
			Class.forName(driver);
			// 获取数据库连接
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
	 * 查询单列数据,在此基础上加上一段汉字
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param values
	 *            要绑定的参数数，若无参数，则传递null
	 * @return 结果集合
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
				String number = "记录的编号是：" + id;
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
	 * 查询单列数据
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param values
	 *            要绑定的参数数，若无参数，则传递null
	 * @return 结果集合
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
	 * 查询多行多列数据
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param values
	 *            要绑定的参数数组，若无参数，则传递null
	 * @return 结果集合
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
	 * 查询单行数据
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param values
	 *            要绑定的参数数组，若无参数，则传递null
	 * @return 结果集合
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
	 * 执行dml语句
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param values
	 *            绑定的参数数组，若无参数，则为null
	 * @return 执行后的行数
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
