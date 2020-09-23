package ar.edu.iua.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties()
public class User implements Serializable{



	private static final long serialVersionUID = 4647570640764087147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 80, nullable = false)
	private String nombre;
	
	@Column(length = 300, nullable = false, unique = true)
	private String email;
	
	@Column(length = 80, nullable = false)
	private String apellido;
	
	@Column(length = 30, nullable = false, unique = true)
	private String username;
	
	@Column(length = 100)
	private String password;
	
	
	
	
	
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = {
	@JoinColumn(name = "id_user", referencedColumnName = "id") }, inverseJoinColumns = {
	@JoinColumn(name = "id_rol", referencedColumnName = "id") })
	private Set<Rol> roles;
/*
	@OneToMany(targetEntity = Venta.class, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Venta> ventaList;
*/
	@ManyToOne
	@JoinColumn(name="id_rol_principal")
	private Rol rolPrincipal;
	
	
		
	/*
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * 
	 * */


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Rol getRolPrincipal() {
		return rolPrincipal;
	}


	public void setRolPrincipal(Rol rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}
/*
	public List<Venta> getVentaList() {
		return ventaList;
	}


	public void setVentaList(List<Venta> ventaList) {
		this.ventaList = ventaList;
	}
	
*/
	
	
	
	
	
	
	
}
