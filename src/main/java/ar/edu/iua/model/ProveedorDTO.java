package ar.edu.iua.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProveedorDTO implements Serializable {
	private Logger log = LoggerFactory.getLogger(this.getClass());


	/**
	* 
	*/
	private static final long serialVersionUID = 7474205942141513611L;
	

	public ProveedorDTO(Long id, String nombre) {
		this.nombre = nombre;
	}
	
	private String nombre;

	public String getNombre() {
		log.debug("[MODEL - PROVEEDOR DTO] get Nombre");
		return "proveedor_" + nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
