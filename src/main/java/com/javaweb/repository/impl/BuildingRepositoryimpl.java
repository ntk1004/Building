package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.attribute.standard.NumberUp;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.javaweb.Util.NumberUtil;
import com.javaweb.Util.Stringutil;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
@Repository
public class BuildingRepositoryimpl implements BuildingRepository{
	private final String DB_URL= "jdbc:mysql://localhost:3306/estatebasic" ;
	private final String USER = "root";
	private final String PASS = "30102004";
	
	public static void settable (BuildingSearchBuilder buildingSearchBuilder,StringBuilder sql) {
		Long staffid = buildingSearchBuilder.getStaffid();
		if (staffid != null) {
			sql.append(" inner join assignmentbuilding on assignmentbuilding.buildingid = b.id");
		}
	if(buildingSearchBuilder.getType() !=null && buildingSearchBuilder.getType().size()!=0) {
		sql.append(" inner join buildingrenttype on buildingrenttype.buildingid = b.id ");
		sql.append( " inner join renttype  on renttype.id =buildingrenttype.renttypeid ");
	}
	Long areadau = buildingSearchBuilder.getAreafrom();
	Long areacuoi = buildingSearchBuilder.getAreato();
	if(areadau!=null ||areacuoi!=null) {
		sql.append(" inner join rentarea on rentarea.buildingid = b.id");
	}

	
	}
	public static void queryNomal (BuildingSearchBuilder buildingSearchBuilder,StringBuilder where) {
		try {
			Field[] field = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : field) {
				item.setAccessible(true);
				String fielname = item.getName(); 
			if (!fielname.equals("staffid")&& !fielname.equals("type") && !fielname.startsWith("area")&&!fielname.startsWith("rent")) {
				Object value = item.get(buildingSearchBuilder);
				if(value != null) {
					if(item.getType().getName().equals("java.lang.Long")){
						where.append(" AND b."+fielname+" = " +value );
					}
					else if (item.getType().getName().equals("java.lang.String")) {
						where.append(" AND b."+ fielname + " like '%" + value + "%' ");
					}
				}
			}
			}
				
		} catch (Exception e) {
		
		}
	}
	public static void querySpecial (BuildingSearchBuilder buildingSearchBuilder ,StringBuilder where) {
		Long Staffid = buildingSearchBuilder.getStaffid();
		if (Staffid != null) {
			where.append(" AND assignmentbuilding.staffid = "+ Staffid );
		}
		Long areadau = buildingSearchBuilder.getAreafrom();
		Long areacuoi = buildingSearchBuilder.getAreato();
		if (areadau != null || areacuoi != null) {
			if (areadau !=null) {
				where.append(" AND rentarea.value >= "+ areadau + " ");
			}
			if (areacuoi != null) {
				where.append(" AND rentarea.value <= " + areacuoi + " ");
			}
		}
		Long rentpriceto = buildingSearchBuilder.getRentpriceto();
		Long rentpricefrom =buildingSearchBuilder.getRentpricefrom();
		if (rentpriceto != null || rentpricefrom != null) {
			if (rentpriceto != null) {
				where.append(" AND b.rentprice <= " + rentpriceto);
			}
			if (rentpricefrom != null) {
				where.append(" AND b.rentprice >= " + rentpricefrom);
			}
		}
		
		if (buildingSearchBuilder.getType() !=null && buildingSearchBuilder.getType().size()!=0) {
			where.append(" and renttype.code in (");
		 String s= buildingSearchBuilder.getType().stream().map(it->"'"+it+"'").collect(Collectors.joining(","))+") ";
		 where.append(s);
		}
	}
@Override
public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
	  List<BuildingEntity> list = new ArrayList<>();
StringBuilder sql = new StringBuilder("select b.id , b.name , b.street,b.ward,b.districtid,b.structure,b.numberofbasement,b.floorarea,b.direction,b.level,b.rentprice,b.rentpricedescription,b.servicefee,b.brokeragefee,b.managername,b.managerphonenumber from building b ");
 settable ( buildingSearchBuilder, sql) ;
StringBuilder where =new StringBuilder (" where 1=1 ");
queryNomal( buildingSearchBuilder,  where);
querySpecial( buildingSearchBuilder, where);
sql.append(where);
sql.append(" group by b.id");

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
