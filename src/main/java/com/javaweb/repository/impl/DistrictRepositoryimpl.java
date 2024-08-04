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

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
@Repository
public class DistrictRepositoryimpl implements DistrictRepository {
	private final String DB_URL= "jdbc:mysql://localhost:3306/estatebasic" ;
	private final String USER = "root";
	private final String PASS = "30102004";
@Override
public List<DistrictEntity> findAll() {
	List<DistrictEntity> de = new ArrayList<>();
	StringBuilder sql = new StringBuilder("select * from district");
	try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			 Statement stmt = conn.createStatement();
	   	    ResultSet rs = stmt.executeQuery(sql.toString());){
		while (rs.next()) {
			DistrictEntity a = new DistrictEntity();
			a.setId(rs.getInt("id"));
			a.setCode(rs.getString("code"));
			a.setName(rs.getString("name"));
			de.add(a);
		}
		
	} catch (Exception e) {
	
	}
	
	return de;
	
}
}
