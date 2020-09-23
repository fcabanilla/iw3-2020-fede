package ar.edu.iua.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="venta")

public class Venta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1861830004923572175L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name ="fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = true)
	private Date fecha;

	@Column(length = 150, nullable = false)
	private Double precioTotalVenta;
	
	@ManyToMany
	@JoinTable(name = "producto_venta", joinColumns = {
	@JoinColumn(name = "id_venta", nullable=false, referencedColumnName = "id") }, inverseJoinColumns = {
	@JoinColumn(name = "id_producto", referencedColumnName = "id") })
	private List<Producto> productoList;
	
	
	
	public Long getId() {
		return id;
	}

	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getPrecioTotalVenta() {
		return precioTotalVenta;
	}

	public void setPrecioTotalVenta(Double precioTotal) {
		this.precioTotalVenta = precioTotal;
	}
}
