/*
 * Project: cic
 * File:    CicRepository.java
 * Created: Feb 7, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.repo;

import org.springframework.data.repository.CrudRepository;

import coetzee.hendrik.cic.entities.Cic;

/**
 * Repository for accessing {@link Cic} entityes.
 * 
 */
public interface CicRepository extends CrudRepository<Cic, Long> {

}
