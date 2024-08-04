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

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
@Repository
public class BuildingRepositoryimpl implements BuildingRepository{
	private final String DB_URL= "jdbc:mysql://localhost:3306/estatebasic" ;
	private final String USER = "root";
	private final String PASS = "30102004";
	
	public static StringBuilder settable (Map<String, Object> param,List <String> type,StringBuilder sql) {
		String staffid = (String)param.get("Staffid");
		if (staffid != null && !staffid.equals("")) {
			sql.append(" inner join assignmentbuilding on assignmentbuilding.buildingid = b.id");
		}
	if(type !=null && type.size()!=0) {
		sql.append(" inner join buildingrenttype on buildingrenttype.buildingid = b.id ");
		sql.append( " inner join renttype  on renttype.id =buildingrenttype.renttypeid ");
	}
	String areadau = (String)param.get("areafrom");
	String areacuoi = (String)param.get("areato");
	if((areadau!=null && !areadau.equals("")||(areacuoi!=null && !areacuoi.equals("")))) {
		sql.append(" inner join rentarea on rentarea.buildingid = b.id");
	}
	return sql;
	
	}
	public static  StringBuilder checktable  (Map<String, Object> param,List <String> type,StringBuilder sql) {
		String ten= (String)param.get("name");
		if (ten != null && !ten.equals("")) {
			sql.append(" and b.name like '%"+ten+"%' ");
			
		}
		String floorarea = (String)param.get("floorarea");
		if (floorarea != null && !floorarea.equals("")) {
			sql.append(" and b.floorarea = "+floorarea+" ");
		}
		String ward = (String)param.get("ward");
		if (ward != null && !ward.equals("")) {
			sql.append(" and b.ward like '%"+ward+"%' ");
		}
		String  street = (String)param.get("street");
		if (street != null && !street.equals("")) {
			sql.append(" and b.street like '%"+street+"%' ");
		} 
		String numberofbasement = (String)param.get("numberofbasement");
		if (numberofbasement!= null && !numberofbasement.equals("")) {
			sql.append(" and b.numberofbasement = "+numberofbasement+" ");
		}
		String direction = (String)param.get("direction");
		if (direction!= null && !direction.equals("")) {
			sql.append(" and b.direction="+direction+" ");
		}
		String level = (String)param.get("level");
		if (level!= null && !level.equals("")) {
			sql.append(" and b.level ="+level+" ");
		}
		String areadau = (String)param.get("areafrom");
		String areacuoi = (String)param.get("areato");
		if((areadau!=null && !areadau.equals("")))
		{
			sql.append(" and rentarea.value >= "+areadau+" ");
		}
		if(areacuoi!=null && !areacuoi.equals("")) {
			sql.append(" and rentarea.value <= "+areacuoi+" ");
		}
		String rentpricefrom = (String)param.get("rentpricefrom");
		String rentpriceto = (String)param.get("rentpriceto");
		if(rentpricefrom!= null && !rentpricefrom.equals("") ) {
			sql.append( " and b.rentprice >= "+rentpricefrom+ " ");
		}
		if(rentpriceto!= null && !rentpriceto.equals("") ) {
			sql.append( " and b.rentprice <= "+rentpriceto+" ");
		}
		String managername = (String)param.get("managername");
		if(managername!= null && !managername.equals("")) {
			sql.append(" and b.managername like '%"+managername+"'% ");
		}
		String managerphone = (String)param.get("managerphone");
		if(managerphone!=null && !managerphone.equals("")) {
			sql.append(" and b.managerphone like '%"+managerphone+"'% ");
		}
		String staffid = (String)param.get("Staffid");
		if (staffid !=null && !staffid.equals("")) {
			sql.append(" and assignmentbuilding.staffid ="+staffid+" ");
		}
		if(type !=null && type.size()!=0) {
	for (int i=0 ;i <type.size();i++) {
		if (i==0) {
			sql.append(" and (renttype.code like '%"+type.get(i)+"%' ");
		}
		else sql.append("or renttype.code like '%"+type.get(i)+"%' ");
	}
	sql.append(")");
		}
		String districtid = (String)param.get("districtid");
		if (districtid != null && !districtid.equals("")) {
			sql.append(" and b.districtid = "+districtid+" ");
		}
		return sql;
		
	}
  @Override
public List<BuildingEntity> findAll( Map<String, Object> param,List <String> type) {
	  List<BuildingEntity> list = new ArrayList<>();
StringBuilder sql = new StringBuilder("select b.id , b.name , b.street,b.ward,b.districtid,b.structure,b.numberofbasement,b.floorarea,b.direction,b.level,b.rentprice,b.rentpricedescription,b.servicefee,b.brokeragefee,b.managername,b.managerphonenumber from building b ");
sql= settable (param, type, sql) ;

sql.append(" where 1=1 ");
sql=checktable(param, type, sql);
sql.append("group by b.id");

     try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
    	 Statement stmt =  conn.createStatement();
    	 ResultSet rs = stmt.executeQuery(sql.toString())){
    		 while (rs.next())
    		 {
    	 BuildingEntity bd = new BuildingEntity();
    	 bd.setName(rs.getString("name"));
    	 bd.setFloorarea(rs.getInt("floorarea"));
    	bd.setStreet(rs.getString("street"));
    	bd.setWard(rs.getString("ward"));              
    	bd.setId(rs.getInt("id"));
        bd.setDistrictid(rs.getInt("districtid"));
    	bd.setDirection(rs.getString("direction"));
    	bd.setLevel(rs.getInt("level"));
    	bd.setNumberOfbasement(rs.getInt("numberofbasement"));
    	bd.setRentprice(rs.getInt("rentprice"));
    	bd.setManagername(rs.getString("managername"));
    	bd.setManagerphone(rs.getString("managerphonenumber"));
    	bd.setBrokeragefee(rs.getInt("brokeragefee"));
    	bd.setServicefee(rs.getInt("servicefee"));
    	 list.add(bd);
    		 }
    	 }
    		 catch (Exception e) {
				
			}
    	 
	return list;
}
}
