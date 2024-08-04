package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.modol.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.rentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.rentArea;
import com.javaweb.service.Buildingservice;
@Service
public class BuildingServiceimpl implements Buildingservice{
	@Autowired
	private BuildingRepository buildingrepository;
	@Autowired
	private rentAreaRepository rentarearepository;
	@Autowired
	private DistrictRepository districtrepository;
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> param, List<String> type) {
		List<BuildingEntity> bd = buildingrepository.findAll(param,type);
		List<rentArea> rt = rentarearepository.Area(param);
		List<DistrictEntity> de = districtrepository.findAll();
		List<BuildingDTO> dto = new ArrayList<>();
		for (BuildingEntity x : bd) {
			BuildingDTO  a = new  BuildingDTO();
			a.setTen_sp(x.getName());
		   
		    a.setSo_tang_ham(x.getNumberOfbasement());
		    a.setTen_quanly(x.getManagername());
		    a.setSdt(x.getManagerphone());
		    a.setArea_san(x.getFloorarea());
		    a.setGia_thue(x.getRentprice());
		 
		    a.setPhi_MG(x.getBrokeragefee());
			for (DistrictEntity t : de) {
				if (x.getDistrictid()==t.getId()) {
					 a.setDia_chi(x.getStreet()+" "+x.getWard()+" "+t.getName());
				}
			}
		    
			if (rt.size()!=0) {
				String m="";
				for (rentArea v : rt) {
					if(x.getId()==v.getBuildingid()) {
						m=m+""+v.getValue()+',';
					}
				}
				m=m.substring(0,m.length()-1);
				a.setArea(m);
			}
			dto.add(a);
		}
		return dto;
	}

}