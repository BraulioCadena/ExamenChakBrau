package com.example.braulio.userapi.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "users")
public class User {
	@Id
	private UUID id;
	
	@Column(nullable= false, unique = true)
	private String email;
	
	@Column(nullable= false)
	private String name;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String password; // AES256 ENCRIPTADO
	
	@Column(name= "tax_id", nullable= false, unique= true)
	private String taxId;
	
	@Column(name= "created_at", nullable= false)
	private String createdAt; // TIEMPO DE MADAGASTACAR FORTAMTO DD-MM-YY
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "user_id")
	private List<Address> addresses;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public User() {
	    //  JPA
	}


	public User(UUID id, String email, String name, String phone, String password, String taxId, String createdAt,
			List<Address> addresses) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.taxId = taxId;
		this.createdAt = createdAt;
		this.addresses = addresses;
	}
	
	
}
