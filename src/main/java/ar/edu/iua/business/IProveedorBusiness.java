package ar.edu.iua.business;

import java.util.List;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Proveedor;
import ar.edu.iua.model.DTO.ProveedorDTO;

public interface IProveedorBusiness {

	public Proveedor load(int id) throws NotFoundException, BusinessException;

	public List<Proveedor> list() throws BusinessException;
	
	public Proveedor add(Proveedor proveedor) throws BusinessException;
	
	public Proveedor update(Proveedor proveedor) throws NotFoundException, BusinessException;
	
	public void delete(int id)throws NotFoundException, BusinessException;
	
	/*
	 * 	METODOS CUSTOMS
	 * */

	public List<ProveedorDTO> listPrefix() throws BusinessException;


}
