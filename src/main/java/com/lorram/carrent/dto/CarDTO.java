package com.lorram.carrent.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lorram.carrent.entities.Car;

public class CarDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer manufactureYear;
	private String model;
	
	List<RentLogDTO> rentLogs = new ArrayList<>();

	public CarDTO() {
	}

	public CarDTO(Long id, Integer manufactureYear, String model, List<RentLogDTO> rentLogs) {
		this.id = id;
		this.manufactureYear = manufactureYear;
		this.model = model;
		this.rentLogs = rentLogs;
	}
	
	public CarDTO(Car entity) {
		id = entity.getId();
		manufactureYear = entity.getManufactureYear();
		model = entity.getModel();
		rentLogs = entity.getRentLogs().stream().map(x -> new RentLogDTO(x)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(Integer manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<RentLogDTO> getRentLogs() {
		return rentLogs;
	}

	public void setRentLogs(List<RentLogDTO> rentLogs) {
		this.rentLogs = rentLogs;
	}

}
