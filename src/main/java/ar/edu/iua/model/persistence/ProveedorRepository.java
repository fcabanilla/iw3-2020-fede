package ar.edu.iua.model.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Proveedor;
import ar.edu.iua.model.ProveedorDTO;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	
	/*
	 * QUERY NATIVAS
	 * */
	@Query(nativeQuery = true)
	public  List<ProveedorDTO> findWithPrefix();
}
