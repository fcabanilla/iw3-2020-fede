package ar.edu.iua.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ar.edu.iua.model.DTO.ProveedorDTO;

@Entity
@Table(name="proveedor")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")


@NamedNativeQuery(
		name 	= "Proveedor.findWithPrefix", 
		query 	= "SELECT p.nombre\n" + 
				"FROM proveedor p", 
		resultSetMapping = "proveedorMap")
@SqlResultSetMapping(
        name = "proveedorMap",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "p.nombre", type = String.class)
                        },
                        targetClass = ProveedorDTO.class
                )
        }
)


public class Proveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8328672099403732928L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonBackReference(value = "idProveedor")
	private int id;
	
	private String nombre;
	
	@JsonBackReference(value = "productoList")
	@OneToMany(targetEntity = Producto.class, mappedBy = "proveedor", fetch = FetchType.LAZY)
	private List<Producto> productoList;
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return "proveedor_" + nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}


	
	

}
