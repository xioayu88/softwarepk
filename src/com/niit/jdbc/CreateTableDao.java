package com.niit.jdbc;

import java.util.*;

/**
 * 在数据库中建表
 * 
 * @author Administrator
 * 
 */

public class CreateTableDao extends AbstractDao {
	public void init() {
		String NAP_sql = "create table if not exists NAP_table "
				+ "(name varchar(20),password varchar(20)); ";
		String admin_sql = "create table if not exists admin_table "
				+ "(id int auto_increment primary key,name varchar(20),"
				+ " password varchar(20)); ";
		String user_sql = "create table if not exists user_table "
				+ "(id int auto_increment primary key,name varchar(20),"
				+ "password varchar(20),amount int,date varchar(20),"
				+ "type varchar(20),description text,remark text);";
		String type_sql = "create table if not exists type_table "
				+ "(id int auto_increment primary key,typeName varchar(20));";
		String stop_sql = "create table if not exists stop_table "
				+ "(id int auto_increment primary key,name varchar(20));";
		dml(NAP_sql, null);
		dml(admin_sql, null);
		dml(user_sql, null);
		dml(type_sql, null);
		dml(stop_sql, null);
		String sql = "select * from NAP_table";
		Vector nameAnd = queryMulData(sql, null);
		if (nameAnd.isEmpty()) {
			String namesql = "insert into NAP_table values('admin','123456');";
			dml(namesql, null);
		}
		String query_sql = "select typeName from type_table";
		Vector types = querySinData(query_sql, null);
		if (!types.contains("salary")) {
			String sal_sql = "insert into type_table values (null,'salary');";
			dml(sal_sql, null);

		}
		if (!types.contains("clothes")) {
			String clo_sql = "insert into type_table values(null,'clothes');";
			dml(clo_sql, null);
		}
		if (!types.contains("food")) {
			String foo_sql = "insert into type_table values(null,'food');";
			dml(foo_sql, null);
		}

	}
}
