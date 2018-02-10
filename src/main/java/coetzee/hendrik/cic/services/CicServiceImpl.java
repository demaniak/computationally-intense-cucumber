/*
 * Project: cic
 * File: CicServiceImpl.java
 * Created: Feb 7, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.services;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.entities.EntityEntity;
import coetzee.hendrik.cic.err.CicRegistrationException;
import coetzee.hendrik.cic.repo.CicRepository;
import coetzee.hendrik.cic.repo.EntityRepository;
import coetzee.hendrik.cic.rest.CicRegistration;

/**
 * Default implementation.
 * 
 */
@Service
public class CicServiceImpl implements CicService {
    private CicRepository cicRepo;
    private EntityRepository entityRepo;

    @Autowired
    public void setCicRepo(CicRepository cicRepo) {
        this.cicRepo = cicRepo;
    }

    @Autowired
    public void setEntityRepo(EntityRepository entityRepo) {
        this.entityRepo = entityRepo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see coetzee.hendrik.cic.services.CicService#register(coetzee.hendrik.cic.rest.CicRegistration)
     */
    @Override
    @Transactional
    public CicEntity register(CicRegistration nuCic) {
        try {
            EntityEntity e = entityRepo.findOne(nuCic.getEntity().getEntityId());
            if (e == null) {
                e = entityRepo.save(nuCic.getEntity());
                nuCic.setEntity(e);
            }
            //@formatter:off
            CicEntity c = CicEntity.builder()
                .body(nuCic.getBody())
                .cicTimestamp(builCalendar (nuCic))
                .cicType(nuCic.getCicType())
                .entity(e)
                .sourceSystem(nuCic.getSourceSystem())
                .subject(nuCic.getSubject())
                .build();
            return cicRepo.save(c);
            //@formatter:on
        } catch (Exception e) {
            throw new CicRegistrationException(e);
        }

    }

    private Calendar builCalendar(CicRegistration nuCic) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(nuCic.getCicTimestamp());
        return cal;
    }

    @Override
    @Transactional
    public CicEntity get(Long cidId) {
        return cicRepo.findOne(cidId);
    }

}
