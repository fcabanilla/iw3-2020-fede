package ar.edu.iua.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Producto;
// import ar.edu.iua.model.Venta;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	// WHERE nombre like "%?nombre%" AND descripcion like "%?descripcion%"
	public List<Producto> findByNombreContainingAndDescripcionContaining(String nombre, String descripcion);
	
	public List<Producto> findByNombreContaining(String nombre);
	
	public List<Producto> findByDescripcionContaining(String descripcion);
	
	public List<Producto> findByprecioListaLessThan(double precio);
	
	public List<Producto> findByprecioListaGreaterThan(double precio);
	
	public List<Producto> findAllByOrderByPrecioListaAsc();
	
	public List<Producto> findAllByOrderByPrecioListaDesc();
	
	public List<Producto> findByNombreStartingWith(String primeraLetra);
	
	/*
	 * QUERY NATIVAS
	 * */
	@Query(	value = "\n" +
			"SELECT * \n" + 
			"FROM testw3.producto p \n" + 
			"INNER JOIN producto_ingrediente_detalle pid ON pid.producto_id = p.id \n" + 
			"INNER JOIN ingredientes i ON i.id = pid.ingrediente_id \n" + 
			"WHERE p.precio_lista > :precio"
			, nativeQuery = true)
	public  List<Producto> findProductoIngredienteByGreaterPrecioLista(@Param("precio") double precioLista);
}
