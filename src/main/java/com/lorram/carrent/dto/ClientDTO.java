package com.lorram.carrent.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lorram.carrent.entities.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	
	private List<RentLogDTO> rentedCars = new ArrayList<>();
	
	public ClientDTO() {
	}
	
	public ClientDTO(Client entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		rentedCars = entity.getRentedCars().stream().map(x -> new RentLogDTO(x)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RentLogDTO> getRentedCars() {
		return rentedCars;
	}

	public void setRentedCars(List<RentLogDTO> rentedCars) {
		this.rentedCars = rentedCars;
	}
}
