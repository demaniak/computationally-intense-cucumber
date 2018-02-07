/*
 * Project: cic
 * File:    CicService.java
 * Created: Feb 7, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.services;

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.rest.CicRegistration;

/**
 * Service for managing {@link CicEntity}'s.
 * 
 */
public interface CicService {

    /**
     * 
     * @param nuCic incomming data
     * @return the persisted instance
     */
    CicEntity register(CicRegistration nuCic);

    /**
     * 
     * @param cidId id of the entity
     * @return the entity
     */
    CicEntity get(Long cidId);

}
