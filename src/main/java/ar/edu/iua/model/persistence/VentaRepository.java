package ar.edu.iua.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.iua.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{
	
	@Query(value = "SELECT * FROM venta v INNER JOIN producto_venta pv ON pv.id_venta = v.id INNER JOIN producto p ON p.id = pv.id_producto WHERE p.id = :id", nativeQuery = true)
	public  List<Venta> findVentasByProducto(@Param("id") long idProducto);
	
	
}