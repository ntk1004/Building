package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
private String name;
private Long floorarea;
private String ward;
private String street;
private Long districtid;
private Long numberofbasement;
private List <String> type =new ArrayList<>();
private String managername;
private String managerphonenumber;
private Long rentpricefrom;
private Long rentpriceto;
private Long areato;
private Long areafrom;
private Long staffid;

private BuildingSearchBuilder (Builder build) {
	this.name = build.name;
	this.floorarea = build.floorarea;
	this.ward = build.ward;
	this.street = build.street;
	this.districtid = build.districtid;
	this.numberofbasement = build.numberofbasement;
	this.type = build.type;
	this.managername = build.managername;
	this.managerphonenumber = build.managerphonenumber;
	this.rentpricefrom = build.rentpricefrom;
	this.rentpriceto = build.rentpriceto;
	this.areato = build.areato;
	this.areafrom = build.areafrom;
	this.staffid = build.staffid;
	 
 }
 public String getName() {
	return name;
}
public Long getFloorarea() {
	return floorarea;
}
public String getWard() {
	return ward;
}
public String getStreet() {
	return street;
}
public Long getDistrictid() {
	return districtid;
}
public Long getNumberofbasement() {
	return numberofbasement;
}
public List<String> getType() {
	return type;
}
public String getManagername() {
	return managername;
}
public String getManagerphonenumber() {
	return managerphonenumber;
}
public Long getRentpricefrom() {
	return rentpricefrom;
}
public Long getRentpriceto() {
	return rentpriceto;
}
public Long getAreato() {
	return areato;
}
public Long getAreafrom() {
	return areafrom;
}
public Long getStaffid() {
	return staffid;
}

 public static class Builder {
	 private String name;
	 private Long floorarea;
	 private String ward;
	 private String street;
	 private Long districtid;
	 private Long numberofbasement;
	 private List <String> type =new ArrayList<>();
	 private String managername;
	 private String managerphonenumber;
	 private Long rentpricefrom;
	 private Long rentpriceto;
	 private Long areato;
	 private Long areafrom;
	 private Long staffid;
	 
	public Builder setName(String name) {
		this.name = name;
		return this;
	}
	public Builder setFloorarea(Long floorarea) {
		this.floorarea = floorarea;
		return this;
	}
	public Builder setWard(String ward) {
		this.ward = ward;
		return this;
	}
	public Builder setStreet(String street) {
		this.street = street;
		return this;
	}
	public Builder setDistrictid(Long districtid) {
		this.districtid = districtid;
		return this;
	}
	public Builder setNumberofbasement(Long numberofbasement) {
		this.numberofbasement = numberofbasement;
		return this;
	}
	public Builder setType(List<String> type) {
		this.type = type;
		return this;
	}
	public Builder setManagername(String managername) {
		this.managername = managername;
		return this;
	}
	public Builder setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
		return this;
	}
	public Builder setRentpricefrom(Long rentpricefrom) {
		this.rentpricefrom = rentpricefrom;
		return this;
	}
	public Builder setRentpriceto(Long rentpriceto) {
		this.rentpriceto = rentpriceto;
		return this;
	}
	public Builder setAreato(Long areato) {
		this.areato = areato;
		return this;
	}
	public Builder setAreafrom(Long areafrom) {
		this.areafrom = areafrom;
		return this;
	}
	public Builder setStaffid(Long staffid) {
		this.staffid = staffid;
		return this;
	}
  public BuildingSearchBuilder build() {
	  return new BuildingSearchBuilder(this);
  }
 }
}
