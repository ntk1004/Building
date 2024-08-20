package com.javaweb.repository.impl;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository

public class BuildingRepositoryimpl implements BuildingRepository{
	
@PersistenceContext
	private EntityManager entityManager; 
@Override
public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
     String sql= "FROM BuildingEntity b";
    Query query = entityManager.createQuery(sql, BuildingEntity.class);
      return query.getResultList();
}
}