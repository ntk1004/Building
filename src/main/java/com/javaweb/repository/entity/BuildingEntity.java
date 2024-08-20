package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column (name="street")
	private String street;
	
	@Column (name = "ward")
	private String ward;
	
//	@Column (name="districtid")
//	private Long districtid;
	
	@Column (name = "structure")
	private String structure;
	
	@Column (name = "numberofbasement")
	private Long numberofbasement;
	
	@Column (name="floorarea")
	private Long floorarea;
	
	@Column (name="direction")
	private String direction;
	
	@Column(name = "level")
	private Long level;
	
	@Column (name = "rentprice")
	private Long rentprice;
	
	@Column (name = "servicefee")
    private Long servicefee;
	
	@Column (name = "brokeragefee")
    private Long brokeragefee;

	
	
	@OneToMany(mappedBy = "building",fetch = FetchType.LAZY)
	private List <rentArea> rent = new ArrayList<>();
	
	public List<rentArea> getRent() {
		return rent;
	}

	public void setRent(List<rentArea> rent) {
		this.rent = rent;
	}
	
	@ManyToOne()
	@JoinColumn(name ="districtid")
	private DistrictEntity district;
	
	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

//	public Long getDistrictid() {
//		return districtid;
//	}
//
//	public void setDistrictid(Long districtid) {
//		this.districtid = districtid;
//	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Long getNumberOfbasement() {
		return numberofbasement;
	}

	public void setNumberOfbasement(Long numberOfbasement) {
		this.numberofbasement = numberOfbasement;
	}

	public Long getFloorarea() {
		return floorarea;
	}

	public void setFloorarea(Long floorarea) {
		this.floorarea = floorarea;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getRentprice() {
		return rentprice;
	}

	public void setRentprice(Long rentprice) {
		this.rentprice = rentprice;
	}

	public Long getServicefee() {
		return servicefee;
	}

	public void setServicefee(Long servicefee) {
		this.servicefee = servicefee;
	}

	public Long getBrokeragefee() {
		return brokeragefee;
	}

	public void setBrokeragefee(Long brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	
}