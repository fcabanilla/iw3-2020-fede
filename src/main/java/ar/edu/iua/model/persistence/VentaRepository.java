package ar.edu.iua.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.iua.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{
	
}