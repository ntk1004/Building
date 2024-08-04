package com.javaweb.api;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.modol.BuildingDTO;
import com.javaweb.service.Buildingservice;

@RestController
 public class BuildingAPI {
	@Autowired
	private Buildingservice buildingservice;
 @GetMapping("/api/building/")
 public List<BuildingDTO> building (@RequestParam Map<String,Object> param,
		                      @RequestParam (value ="type" , required = false) List <String> type) {
	 List <BuildingDTO> buildngdto = buildingservice.findAll(param, type);		
	return buildngdto;
}
 
 }
