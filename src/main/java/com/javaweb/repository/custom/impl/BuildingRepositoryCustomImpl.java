package com.javaweb.repository.custom.impl;


import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
@Primary
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom{
	//JPA
	@PersistenceContext
	private EntityManager entityManager; 
	
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
			//java reflection
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
	//JPQL :JPA query L
	//String sql = "From BuildingEntity b";
	// Query query = entityManager.createQuery(sql,BuildingEntity.class);
	
	//SQL Native  
StringBuilder sql = new StringBuilder("select b.* from building b ");
 settable ( buildingSearchBuilder, sql) ;
StringBuilder where =new StringBuilder (" where 1=1 ");
queryNomal( buildingSearchBuilder,  where);
querySpecial( buildingSearchBuilder, where);
sql.append(where);
sql.append(" group by b.id");
Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
   return query.getResultList();
}
}



