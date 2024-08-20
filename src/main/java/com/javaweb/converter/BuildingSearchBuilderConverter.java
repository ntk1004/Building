package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.Util.MapUtil;
import com.javaweb.builder.BuildingSearchBuilder;

@Component
public class BuildingSearchBuilderConverter {
 public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> param ,List<String> type) {
	 
	 BuildingSearchBuilder buildingsearchbuider = new BuildingSearchBuilder.Builder()
                                                                                   .setName(MapUtil.getObject(param,"name", String.class))
                                                                                   .setFloorarea(MapUtil.getObject(param,"floorarea", Long.class))
                                                                                   .setWard(MapUtil.getObject(param,"ward", String.class))
                                                                                   .setStreet(MapUtil.getObject(param,"street", String.class))
                                                                                   .setDistrictid(MapUtil.getObject(param, "districtid", Long.class))
                                                                                   .setStaffid(MapUtil.getObject(param,"staffid", Long.class))
                                                                                   .setAreafrom(MapUtil.getObject(param, "areafrom", Long.class))
                                                                                   .setAreato(MapUtil.getObject(param, "areato", Long.class))
                                                                                   .setRentpricefrom(MapUtil.getObject(param, "rentpricefrom", Long.class))
                                                                                   .setRentpriceto(MapUtil.getObject(param, "rentpriceto", Long.class))
                                                                                   .setManagername(MapUtil.getObject(param, "managername", String.class))
                                                                                   .setManagerphonenumber(MapUtil.getObject(param, "managerphonenumber", String.class))
                                                                                   .setType(type)
	                                                                               .setNumberofbasement(MapUtil.getObject(param, "numberofbasement", Long.class))
	                                                                               .build();
	 
                
    return buildingsearchbuider;                                                                                 
 }
}
