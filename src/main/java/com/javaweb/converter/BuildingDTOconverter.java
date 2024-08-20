package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.modol.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.rentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.rentArea;

@Component
public class BuildingDTOconverter {
	@Autowired 
private ModelMapper modelMapper;
	
public BuildingDTO toBuildingdto (BuildingEntity x) {
	BuildingDTO a = modelMapper.map(x, BuildingDTO.class);
 
    a.setDia_chi(x.getStreet()+" "+x.getWard()+" "+x.getDistrict().getName());
    List<rentArea> rt = x.getRent();
String arearesult  =  rt.stream().distinct().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		a.setArea(arearesult);
	return a;
}
}
