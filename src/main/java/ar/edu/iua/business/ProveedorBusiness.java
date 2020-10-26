package ar.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Proveedor;
import ar.edu.iua.model.ProveedorDTO;
import ar.edu.iua.model.persistence.ProveedorRepository;


@Service
public class ProveedorBusiness implements IProveedorBusiness{
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProveedorRepository proveedorDAO;
	@Override
	public Proveedor load(int id) throws NotFoundException, BusinessException {
		Optional<Proveedor> op;
		try {
			op = proveedorDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("La Venta con el id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Proveedor> list() throws BusinessException {
		try {
			log.debug("[BUSINESS] - findWithPrefix - Proveedor Con prefijo");
			return proveedorDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Proveedor add(Proveedor proveedor) throws BusinessException {
		try {

			return proveedorDAO.save(proveedor);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Proveedor update(Proveedor proveedor) throws NotFoundException, BusinessException {
		load(proveedor.getId());
		return proveedorDAO.save(proveedor);
	}

	@Override
	public void delete(int id) throws NotFoundException, BusinessException {
		try {
			proveedorDAO.deleteById(id);
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra el proveedor id = " + id + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public List<ProveedorDTO> listPrefix() throws BusinessException {
		try {
			log.debug("[BUSINESS] - findWithPrefix - Proveedor Con prefijo");
			return proveedorDAO.findWithPrefix();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
