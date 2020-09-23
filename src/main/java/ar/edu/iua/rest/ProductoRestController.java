package ar.edu.iua.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.business.IProductoBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

@RestController
@RequestMapping(value = Constantes.URL_PRODUCTOS)
public class ProductoRestController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IProductoBusiness productoBusiness;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> load(@PathVariable("id") Long id) {

		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "letra/{letra}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> list(@PathVariable("letra") String letra) {

		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.listFirstLetter(letra), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> list(

			@RequestParam(name = "precio", required = false, defaultValue = "-1") String precio,
			@RequestParam(name = "nombre", required = false, defaultValue = "*") String nombre,
			@RequestParam(name = "descripcion", required = false, defaultValue = "*") String descripcion,
			@RequestParam(name = "precio-orden", required = false, defaultValue = "mayor") String precioOrden,
			@RequestParam(name = "order-asc", required = false, defaultValue = "false") String orderAsc,
			@RequestParam(name = "order-desc", required = false, defaultValue = "false") String orderDesc
			) {
		
		try {

			if (!nombre.equals("*") && !descripcion.equals("*")) {
				return new ResponseEntity<List<Producto>>(
						productoBusiness.listByNameAndDescirption(nombre, descripcion), HttpStatus.OK);
			}
			if (!nombre.equals("*")) {
				return new ResponseEntity<List<Producto>>(productoBusiness.listByName(nombre), HttpStatus.OK);
			}
			if (!descripcion.equals("*")) {
				return new ResponseEntity<List<Producto>>(productoBusiness.listByDescription(descripcion),
						HttpStatus.OK);
			}
			if (!precio.equals("-1")) {
				log.error("Se ejecuta productoBusiness.list(precio, precioOrden)");
				// retorno la lista de productos con filtro por precio y precio orden
				return new ResponseEntity<List<Producto>>(
						productoBusiness.listPrice(Double.parseDouble(precio), precioOrden), HttpStatus.OK);
			}
			if (orderAsc.equals("true"))
				return new ResponseEntity<List<Producto>>(productoBusiness.listOrderBy("ASC"), HttpStatus.OK);
			if (orderDesc.equals("true"))
				return new ResponseEntity<List<Producto>>(productoBusiness.listOrderBy("DESC"), HttpStatus.OK);
			return new ResponseEntity<List<Producto>>(productoBusiness.list(), HttpStatus.OK);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Producto producto) {
		try {
			productoBusiness.add(producto);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + '/' + producto.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Producto producto) {
		try {
			productoBusiness.update(producto);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			// e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			// e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			productoBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
