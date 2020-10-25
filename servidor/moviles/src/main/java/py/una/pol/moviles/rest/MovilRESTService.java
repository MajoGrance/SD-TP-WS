/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package py.una.pol.moviles.rest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.una.pol.moviles.model.Movil;
import py.una.pol.moviles.service.MovilService;
import py.una.pol.moviles.service.UbicacionService;

/**
 * JAX-RS Example
 * <p/>
 * Esta clase produce un servicio RESTful para leer y escribir contenido de moviles
 */
@Path("/moviles")
@RequestScoped
public class MovilRESTService {

    @Inject
    MovilService movilService; 
    UbicacionService ubicacionService;
    
    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Movil m) {

        Response.ResponseBuilder builder = null;
        if (!m.getTipo().equals("auto") && !m.getTipo().equals("avion") && !m.getTipo().equals("embarcacion")) {
        	Map<String, String> responseObj = new HashMap<>();
        	responseObj.put("error",m.getTipo() + " no es un tipo de movil valido");
        	return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
        }
        try {
            movilService.crear(m);
            // Create an "ok" response
            
            //builder = Response.ok();
            builder = Response.status(201).entity("Movil creado exitosamente");
            
        } catch (SQLException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("bd-error", e.getLocalizedMessage());
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }
}
