package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {

}
