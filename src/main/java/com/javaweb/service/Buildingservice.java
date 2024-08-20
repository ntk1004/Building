package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.modol.BuildingDTO;

public interface Buildingservice {
 List <BuildingDTO> findAll (Map<String,Object> param, List<String> type);
 
}
