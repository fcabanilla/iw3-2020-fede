package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

public interface IProductoBusiness {

	/*
	 * 
	 * SERVICIOS BASICOS CRUD REST
	 * 
	*/
	public Producto load(Long id) throws NotFoundException, BusinessException;
	
	public List<Producto> list() throws BusinessException;
	
	public Producto add(Producto producto) throws BusinessException;
	
	public Producto update(Producto producto) throws NotFoundException, BusinessException;
	
	public void delete(Long id)throws NotFoundException, BusinessException;
	
	/*
	 * 
	 * SERVICIOS EXTRA REST
	 * 
	*/
	public List<Producto> listByNameAndDescirption(String nombre, String Descripcion) throws BusinessException;
	
	public List<Producto> listByName(String nombre) throws BusinessException;
	
	public List<Producto> listByDescription(String descripcion) throws BusinessException;
	
	public List<Producto> listPrice(Double precio, String precioOrden) throws BusinessException;
	
	public List<Producto> listOrderBy(String tipoOrden) throws BusinessException;
	
	public List<Producto> listFirstLetter(String primeraLetra) throws BusinessException;
	
}
