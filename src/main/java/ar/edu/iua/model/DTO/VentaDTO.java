package ar.edu.iua.model.DTO;

import java.io.Serializable;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VentaDTO implements Serializable {
	private Logger log = LoggerFactory.getLogger(this.getClass());


	/**
	* 
	*/
	private static final long serialVersionUID = 7474205942141513611L;
	
	public VentaDTO(Date fecha, String producto) {
		log.debug("[MODEL - VENTA DTO] Constructor");
		this.fecha = fecha;
		this.productName = producto;
	}

	private Date fecha;
	private String productName;



	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
}
