/*
 * Project: cic
 * File:    CicRepository.java
 * Created: Feb 7, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.repo;

import org.springframework.data.repository.CrudRepository;

import coetzee.hendrik.cic.entities.CicEntity;

/**
 * Repository for accessing {@link CicEntity} entityes.
 * 
 */
public interface CicRepository extends CrudRepository<CicEntity, Long> {

}
