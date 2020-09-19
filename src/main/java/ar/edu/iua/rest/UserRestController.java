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

import ar.edu.iua.business.IUserBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.User;

@RestController
@RequestMapping(value = Constantes.URL_USERS)
public class UserRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserBusiness userBusiness;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> load(@PathVariable("id") Integer id) {

		try {
			return new ResponseEntity<User>(userBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> list() {
		try {

			return new ResponseEntity<List<User>>(userBusiness.list(), HttpStatus.OK);

		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody User user) {
		try {
			userBusiness.add(user);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_USERS + "/" + user.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody User user) {
		try {
			userBusiness.update(user);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
