package ar.edu.iua.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.business.IVentaBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;

@RestController
@RequestMapping(value = Constantes.URL_VENTAS)
public class VentaRestController {
	
	@Autowired
	private IVentaBusiness ventaBusiness;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> load(@PathVariable("id") Long id){
		try {
			return new ResponseEntity<Venta>(ventaBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Venta>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Venta>(HttpStatus.NOT_FOUND);
		}		
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> list() {
		try {

			return new ResponseEntity<List<Venta>>(ventaBusiness.list(), HttpStatus.OK);

		} catch (BusinessException e) {
			//log.error(e.getMessage(), e);
			return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Venta venta) {
		try {
			ventaBusiness.add(venta);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + '/' + venta.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> update(@RequestBody Venta venta) {
		try {
			ventaBusiness.update(venta);
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

	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			ventaBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "venta-producto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> list(@PathVariable("id") Long id) {

		try {
			return new ResponseEntity<List<Venta>>(ventaBusiness.listByProduct(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
