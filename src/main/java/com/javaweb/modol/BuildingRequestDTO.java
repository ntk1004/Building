package com.javaweb.modol;

public class BuildingRequestDTO {
private String name;
private String ward;
private String street;
private Long districtid;
private Long rentprice;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getWard() {
	return ward;
}
public void setWard(String ward) {
	this.ward = ward;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public Long getDistrictid() {
	return districtid;
}
public void setDistrictid(Long districtid) {
	this.districtid = districtid;
}
public Long getRentprice() {
	return rentprice;
}
public void setRentprice(Long rentprice) {
	this.rentprice = rentprice;
}

}
