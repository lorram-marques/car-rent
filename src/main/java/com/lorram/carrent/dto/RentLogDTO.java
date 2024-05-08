package com.lorram.carrent.dto;

import java.io.Serializable;
import java.time.Instant;

import com.lorram.carrent.entities.RentLog;

public class RentLogDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant rentDate;
	private Instant returnDate;
	private Long carId;
	private Long clientId;
		
	public RentLogDTO() {
	}

	public RentLogDTO(Long id, Instant rentDate, Instant returnDate, Long carId, Long clientId) {
		this.id = id;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.carId = carId;
		this.clientId = clientId;
	}
	
	public RentLogDTO(RentLog entity) {
		id = entity.getId();
		rentDate = entity.getRentDate();
		returnDate = entity.getRentDate();
		carId = entity.getCar().getId();
		clientId = entity.getClient().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getRentDate() {
		return rentDate;
	}

	public void setRentDate(Instant rentDate) {
		this.rentDate = rentDate;
	}

	public Instant getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Instant returnDate) {
		this.returnDate = returnDate;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
}
