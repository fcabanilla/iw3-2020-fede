package ar.edu.iua.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.business.IProveedorBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Proveedor;
import ar.edu.iua.model.ProveedorDTO;

@RestController
@RequestMapping(value = Constantes.URL_PROVEEDOR)
public class ProveedorRestController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private IProveedorBusiness proveedorBusiness;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Proveedor> load(@PathVariable("id") int id){
		try {
			return new ResponseEntity<Proveedor>(proveedorBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Proveedor>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Proveedor>(HttpStatus.NOT_FOUND);
		}		
	}
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Proveedor>> list() {
		try {
			log.debug("[REST] - findWithPrefix - Proveedor Con prefijo");
			return new ResponseEntity<List<Proveedor>>(proveedorBusiness.list(), HttpStatus.OK);

		} catch (BusinessException e) {
			//log.error(e.getMessage(), e);
			return new ResponseEntity<List<Proveedor>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Proveedor proveedor) {
		try {
			proveedorBusiness.add(proveedor);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + '/' + proveedor.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Proveedor proveedor) {
		try {
			proveedorBusiness.update(proveedor);
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
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		try {
			proveedorBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
    /*@GetMapping(value = "/buscar_precio")
    public ResponseEntity<List<ProveedorDTO>> loadPrecio(@RequestParam("precio") double p) {
        try {
            return new ResponseEntity<List<ProveedorDTO>>(productoBusiness.findByElPrecio(p), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<List<ProveedorDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<List<ProductoDTO>>(HttpStatus.NOT_FOUND);
        }
    }
    */
}
