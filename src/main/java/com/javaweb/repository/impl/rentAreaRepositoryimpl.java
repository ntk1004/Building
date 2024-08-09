package com.javaweb.repository.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.rentAreaRepository;
import com.javaweb.repository.entity.rentArea;
@Repository
public class rentAreaRepositoryimpl implements rentAreaRepository {

private final String DB_URL= "jdbc:mysql://localhost:3306/estatebasic" ;
private final String USER = "root";
private final String PASS = "30102004";
@Override
public List<rentArea> Area(Integer id) {
		List <rentArea> rt = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select id , value , buildingid from rentarea");
		sql.append(" where rentarea.buildingid = "+id+" ");
		try(Connection conm = DriverManager.getConnection(DB_URL,USER,PASS);
			   	 Statement stmt = conm.createStatement();
		   	    ResultSet rs = stmt.executeQuery(sql.toString());){
			while(rs.next()) {
			rentArea are = new rentArea();
				are.setValue(rs.getInt("value"));
				rt.add(are);
			}
		} catch (Exception e) {
			
		}
	return rt;
	}
}
