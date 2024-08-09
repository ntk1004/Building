package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOconverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.modol.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.Buildingservice;
@Service
public class BuildingServiceimpl implements Buildingservice{
	@Autowired
	private BuildingRepository buildingrepository;
	@Autowired
	private BuildingDTOconverter buildingDTOconverter;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilder;
	@Override
	
	public List<BuildingDTO> findAll(Map<String, Object> param, List<String> type) {
	
		BuildingSearchBuilder b = buildingSearchBuilder.toBuildingSearchBuilder( param,  type);
		List<BuildingEntity> bd = buildingrepository.findAll(b);
 List<BuildingDTO> dto = new ArrayList<>();
		for (BuildingEntity x : bd) {
			BuildingDTO bui = buildingDTOconverter.toBuildingdto(x);
			dto.add(bui);
		}
		return dto;
	}

}