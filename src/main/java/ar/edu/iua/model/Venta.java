package ar.edu.iua.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import ar.edu.iua.model.DTO.VentaDTO;

@Entity
@Table(name="venta")
/*
 * CONSULTAR COMO HACER UN LIKE CON NATIVE QUERY NAMED
 * */
@NamedNativeQuery(
		name = "Venta.findByProductWithXInTheName", 
		query = "SELECT p.nombre AS Producto, v.fecha AS Fecha\n" + 
				"FROM venta v\n" + 
				"INNER JOIN producto_venta pv ON pv.id_venta = v.id\n" + 
				"INNER JOIN producto p ON pv.id_producto = p.id\n" + 
				"WHERE p.nombre LIKE ?1", 
		resultSetMapping = "ventaMap")

@NamedNativeQuery(
		name = "Venta.findByProductX", 
		query = "SELECT p.nombre AS Producto, v.fecha AS Fecha\n" + 
				"FROM venta v\n" + 
				"INNER JOIN producto_venta pv ON pv.id_venta = v.id\n" + 
				"INNER JOIN producto p ON pv.id_producto = p.id\n" + 
				"WHERE p.nombre = ?1", 
		resultSetMapping = "ventaMap")
@SqlResultSetMapping(
        name="ventaMap",
        classes = {
                @ConstructorResult(
                        columns = {                               
                                @ColumnResult(name = "Fecha"	, type = Date.class),
                                @ColumnResult(name = "Producto"	, type = String.class),
                        },
                        targetClass = VentaDTO.class
                )
        }
)



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
