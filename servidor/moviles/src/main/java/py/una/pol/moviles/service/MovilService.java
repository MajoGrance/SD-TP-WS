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
import javax.inject.Inject;

import py.una.pol.moviles.dao.MovilDAO;
import py.una.pol.moviles.model.Movil;

import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MovilService {

    @Inject
    private Logger log;

    @Inject
    private MovilDAO dao;

    public void crear(Movil m) throws Exception {
        log.info("Creando Movil: " + m.getIdentificador() + " " + m.getTipo());
        try {
        	dao.insertar(m);
        }catch(Exception e) {
        	log.severe("ERROR al crear movil: " + e.getLocalizedMessage() );
        	throw e;
        }
        log.info("Movil creada con éxito: " + m.getIdentificador() + " " + m.getTipo() );
    }
    
    public List<Movil> seleccionar() {
    	return dao.seleccionar();
    }
    
    public Movil seleccionarPorIdentificador(String identificador) {
    	return dao.seleccionarPorIdentificador(identificador);
    }
    
    public String borrar(String identificador) throws Exception {
    	return dao.borrar(identificador);
    }
}
