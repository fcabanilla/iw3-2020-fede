package ar.edu.iua.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ingredientes")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Ingrediente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 65234615485796729L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100)
	private String descripcion;

	@ManyToMany(mappedBy = "ingredienteList")
	@JsonBackReference
	private List<Producto> productoList;
	
	/*
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}
	
	
}
