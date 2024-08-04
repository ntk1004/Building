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
	public List<rentArea> Area(Map<String, Object> param) {
		List <rentArea> rt = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select id , value , buildingid from rentarea");
		sql.append(" where 1=1");
		String areadau = (String)param.get("areafrom");
		String areacuoi = (String)param.get("areato");
		if((areadau!=null && !areadau.equals("")))
		{
			sql.append(" and rentarea.value >= "+areadau+" ");
		}
		if(areacuoi!=null && !areacuoi.equals("")) {
			sql.append(" and rentarea.value <= "+areacuoi+" ");
		}
		try(Connection conm = DriverManager.getConnection(DB_URL,USER,PASS);
			   	 Statement stmt = conm.createStatement();
		   	    ResultSet rs = stmt.executeQuery(sql.toString());){
			while(rs.next()) {
			rentArea are = new rentArea();
				are.setValue(rs.getInt("value"));
				are.setBuildingid(rs.getInt("buildingid"));
				rt.add(are);
			}
		} catch (Exception e) {
			
		}
	return rt;
	}
}
