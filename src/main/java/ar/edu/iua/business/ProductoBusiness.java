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
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.persistence.ProductoRepository;

@Service
public class ProductoBusiness implements IProductoBusiness {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductoRepository productoDAO;

	@Override
	public Producto load(Long id) throws NotFoundException, BusinessException {
		Optional<Producto> op;
		try {
			op = productoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El Producto con el id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto add(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		try {
			productoDAO.deleteById(id);
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra el producto id = " + id + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Producto update(Producto producto) throws NotFoundException, BusinessException {
		load(producto.getId());
		return productoDAO.save(producto);

	}

	@Override
	public List<Producto> listByNameAndDescirption(String nombre, String descripcion) throws BusinessException {
		try {
			return productoDAO.findByNombreContainingAndDescripcionContaining(nombre, descripcion);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listPrice(Double precio, String precioOrden) throws BusinessException {
		try {
			log.info("Precio" + precio + "\nPrecio Orden" + precioOrden + "\n");
			if (precioOrden.equals("mayor")) {
				return productoDAO.findByprecioListaGreaterThan(precio);
			}
			return productoDAO.findByprecioListaLessThan(precio);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listByName(String nombre) throws BusinessException {
		try {
			return productoDAO.findByNombreContaining(nombre);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listByDescription(String descripcion) throws BusinessException {
		try {
			return productoDAO.findByNombreContaining(descripcion);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listOrderBy(String tipoOrden) throws BusinessException {
		try {
			if (tipoOrden.equals("ASC")) {
				return productoDAO.findAllByOrderByPrecioListaAsc();
			}
			if (tipoOrden.equals("DESC")) {
				return productoDAO.findAllByOrderByPrecioListaDesc();
			}
			throw new BusinessException();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listFirstLetter(String primeraLetra) throws BusinessException {
		try {
			return productoDAO.findByNombreStartingWith(primeraLetra);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
