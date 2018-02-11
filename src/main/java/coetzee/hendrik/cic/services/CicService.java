/*
 * Project: cic
 * File:    CicService.java
 * Created: Feb 7, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.services;

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.entities.EntityEntity;
import coetzee.hendrik.cic.err.CicNotFoundException;
import coetzee.hendrik.cic.err.CicRegistrationException;
import coetzee.hendrik.cic.rest.CicRegistration;

/**
 * Service for managing {@link CicEntity}'s.
 * 
 */
public interface CicService {

    /**
     * Registers a new {@link CicEntity}. If the linked {@link EntityEntity} does not exist, it will be created. 
     * @param nuCic incoming data
     * @return the persisted instance
     * @throws CicRegistrationException if something goes wrong during record creation.
     */
    CicEntity register(CicRegistration nuCic);

    /**
     * Fetches the specified {@link CicEntity}.
     * @param cidId id of the entity
     * @return the entity
     * @throws CicNotFoundException if {@link CicEntity} not found
     */
    CicEntity get(Long cidId);

}
