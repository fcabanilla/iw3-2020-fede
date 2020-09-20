package ar.edu.iua.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

	@Column(name = "fecha", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fecha;
	
	@Column
	private Double precioTotalVenta;

	
	@Column
	private int cantidadTotalProducto;
	
	/*
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	*/
	/*
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	*/
	
	
	
	@OneToMany(mappedBy = "venta")
	Set<VentaDetalle> ventaDetalle;
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * **********************************************************
	 * */
	/*
	public void setUser(User user) {
		this.user = user;
	}
	*/
	
	public Long getId() {
		return id;
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

	public int getCantidadTotalProducto() {
		return cantidadTotalProducto;
	}

	public void setCantidadTotalProducto(int cantidadTodalProducto) {
		this.cantidadTotalProducto = cantidadTodalProducto;
	}
/*
	public User getUser() {
		return user;
	}
*/
	public Set<VentaDetalle> getVentaDetalle() {
		return ventaDetalle;
	}

	public void setVentaDetalle(Set<VentaDetalle> ventaDetalle) {
		this.ventaDetalle = ventaDetalle;
	}

}
