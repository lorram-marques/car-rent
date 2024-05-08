package com.lorram.carrent.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_rentlog")
public class RentLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant rentDate;
	private Instant returnDate;
	
	@ManyToOne
	@JoinTable(name = "tb_rentlog_car",
		joinColumns = @JoinColumn(name = "rentlog_id"),
		inverseJoinColumns = @JoinColumn(name = "car_id"))
	private Car car;
	
	@ManyToOne
	@JoinTable(name = "tb_rentlog_client",
		joinColumns = @JoinColumn(name = "rentlog_id"),
		inverseJoinColumns = @JoinColumn(name = "client_id"))
	private Client client;
	
	public RentLog() {
	}

	public RentLog(Long id, Instant rentDate, Instant returnDate, Car car, Client client) {
		this.id = id;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.car = car;
		this.client = client;
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentLog other = (RentLog) obj;
		return Objects.equals(id, other.id);
	}
}
