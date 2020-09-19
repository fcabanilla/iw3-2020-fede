package ar.edu.iua.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto_detalle")
public class ProductoDetalle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8663005936400770135L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150)
	private String fabrica;
	
	/*
	 * GETTERS AND SETTERS
	 * */
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFabrica() {
		return fabrica;
	}

	public void setFabrica(String fabrica) {
		this.fabrica = fabrica;
	}
}


