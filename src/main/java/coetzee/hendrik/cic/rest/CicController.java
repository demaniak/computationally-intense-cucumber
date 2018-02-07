/*
 * Project: cic
 * File:    CicController.java
 * Created: Feb 7, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.services.CicService;

/**
 * Rest Controller for {@link CicEntity}'s 
 */
@RestController
public class CicController {
    
    private CicService cicServ;
    
    @Autowired
    public void setCicServ(CicService cicServ) {
        this.cicServ = cicServ;
    }

    @RequestMapping(method=POST, path="/cic")
    public Callable<ResponseEntity<CicEntity>> registerCic (@RequestBody CicRegistration nuCic) {
        return () -> ResponseEntity.ok(cicServ.register (nuCic));
    }
    
    @RequestMapping (method = GET, path = "/cic/{cidId}")
    public Callable<ResponseEntity<CicEntity>> get (@PathVariable Long cidId) {
        return () -> ResponseEntity.ok(cicServ.get (cidId));
    }
}
