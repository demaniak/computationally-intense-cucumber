/*
 * Project: cic
 * File: CicController.java
 * Created: Feb 7, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.net.URI;
import java.util.concurrent.Callable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.err.CicNotFoundException;
import coetzee.hendrik.cic.err.CicRegistrationException;
import coetzee.hendrik.cic.services.CicService;
import io.swagger.annotations.ApiOperation;

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

    @ApiOperation(value="Registers a new Cic.",notes="If the attached Entity does not exist, it will be created.")
    @RequestMapping(method = POST, path = "/cic")
    public Callable<ResponseEntity<Object>> registerCic(@Valid @RequestBody CicRegistration nuCic) {
        return () -> {            
            final CicEntity result = cicServ.register(nuCic);
            final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cidId}")
                    .buildAndExpand(result.getCicId()).toUri();
            return ResponseEntity.created(uri).build();
        };
    }

    @ApiOperation(value="Retrieves an existing Cic.")
    @RequestMapping(method = GET, path = "/cic/{cidId}")
    public Callable<ResponseEntity<CicEntity>> get(@PathVariable Long cidId) {
        return () -> ResponseEntity.ok(cicServ.get(cidId));
    }
    
    @ExceptionHandler(CicRegistrationException.class)
    public ResponseEntity<Object> handleCicRegistrationException (CicRegistrationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
    }
    
    @ExceptionHandler(CicNotFoundException.class)
    public ResponseEntity<Object> handleCicNotFoundException (CicNotFoundException e) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
    }
}
