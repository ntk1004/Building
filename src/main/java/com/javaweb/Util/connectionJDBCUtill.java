package com.javaweb.Util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
@PropertySource("classpath:appliacation.properties")
public class connectionJDBCUtill {
	@Value("${spring.datasource.url}")
	static String DB_URL= "jdbc:mysql://localhost:3306/estatebasic" ;
	@Value("${spring.datasource.username}")
	static String USER = "root";
	@Value("${spring.datasource.password}")
	static  String PASS = "30102004";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (Exception e) {
	
		}
		return null;
	}
}
