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
package py.una.pol.moviles.service;


import javax.ejb.Stateless;
import java.lang.Math;
import javax.inject.Inject;
import java.util.ArrayList;
import py.una.pol.moviles.dao.UbicacionDAO;
import py.una.pol.moviles.model.Ubicacion;

import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class UbicacionService {

    @Inject
    private Logger log;

    @Inject
    private UbicacionDAO dao;

    public void crear(Ubicacion u) throws Exception {
        log.info("Creando Ubicacion: " + u.getMovil() + ", Latitud:" + u.getLatitud() + ", Longitud:" + u.getLongitud());
        try {
        	dao.insertar(u);
        }catch(Exception e) {
        	log.severe("ERROR al crear ubicacion: " + e.getLocalizedMessage() );
        	throw e;
        }
        log.info("Ubicacion creada con éxito: " + u.getMovil() + ", Latitud:" + u.getLatitud() + ", Longitud:" + u.getLongitud());
    }
    
    public List<Ubicacion> seleccionar() {
    	return dao.seleccionar();
    }
    
    public List<Ubicacion> seleccionar(Float latitud, Float longitud, Float metros) {
    	List<Ubicacion> seleccionados = new ArrayList<Ubicacion>();
    	List<Ubicacion> todos = dao.seleccionar();
    	for(int i = 0; i < todos.size(); i++) {
    		double dlat = latitud - todos.get(i).getLatitud();
    		double dlon = longitud - todos.get(i).getLongitud();
    		double distanciad = Math.pow(dlat,2) + Math.pow(dlon,2);
    		Float distancia = new Float(Math.sqrt((distanciad)));
    		if (distancia <= distancia) {
    			seleccionados.add(todos.get(i));
    		}
    	}
    	return seleccionados;
    }
    
    public Ubicacion seleccionarPorId(Long id) {
    	return dao.seleccionarPorId(id);
    }
    
    public Long borrar(Long id) throws Exception {
    	return dao.borrar(id);
    }
}
