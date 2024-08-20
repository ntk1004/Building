package com.javaweb.api;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.modol.BuildingDTO;
import com.javaweb.modol.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.Buildingservice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
@PropertySource("classpath:application.properties")
 public class BuildingAPI {

	@Autowired
	private Buildingservice buildingservice;
	 
	 @PersistenceContext
	 private EntityManager entityManager; 

 @GetMapping("/api/building/")
 public List<BuildingDTO> building (@RequestParam Map<String,Object> param,
		                            @RequestParam (value ="type" , required = false) List <String> type) {
	 List <BuildingDTO> buildngdto = buildingservice.findAll(param, type);		
	return buildngdto;
}
// @DeleteMapping("/api/building/{id}")
//   public void delet(@PathParam(value ="id") Integer id) {
//	 System.out.print(data);
// }

@PostMapping("/api/building/")
@Transactional
public void creatBuilding (@RequestBody BuildingRequestDTO buildingRequestDTO) {
	BuildingEntity buildingEntity = new BuildingEntity();
	buildingEntity.setName(buildingRequestDTO.getName());
	buildingEntity.setStreet(buildingRequestDTO.getStreet());
	buildingEntity.setWard(buildingRequestDTO.getWard());
	buildingEntity.setRentprice(buildingRequestDTO.getRentprice());
	DistrictEntity districtEntity = new DistrictEntity();
	districtEntity.setId(buildingRequestDTO.getDistrictid());
	buildingEntity.setDistrict(districtEntity);
	entityManager.persist(buildingEntity);
	
}
@PutMapping("/api/building/")
@Transactional
public void update (@RequestBody BuildingRequestDTO buildingRequestDTO) {
	BuildingEntity buildingEntity = new BuildingEntity();
	buildingEntity.setId(2L);
	buildingEntity.setName(buildingRequestDTO.getName());
	buildingEntity.setStreet(buildingRequestDTO.getStreet());
	buildingEntity.setWard(buildingRequestDTO.getWard());
	buildingEntity.setRentprice(buildingRequestDTO.getRentprice());
	DistrictEntity districtEntity = new DistrictEntity();
	districtEntity.setId(buildingRequestDTO.getDistrictid());
	buildingEntity.setDistrict(districtEntity);
	entityManager.merge(buildingEntity);
}
@DeleteMapping(value="/api/building/{id}")
private void delete (@PathVariable Long id) {
	BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
	entityManager.remove(buildingEntity);
}
}