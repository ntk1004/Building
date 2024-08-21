package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
 void deleteByIdIn(Long[] ids);  //xóa nhiều id cùng lúc
 List<BuildingEntity> findByNameContaining(String s);  //tim tên giống như like %b%
 List<BuildingEntity> findByNameContainingAndStreet(String name,String street);   //tìm tên theo %% và street
}
