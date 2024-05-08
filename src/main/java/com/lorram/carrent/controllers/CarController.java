package com.lorram.carrent.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lorram.carrent.dto.CarDTO;
import com.lorram.carrent.services.CarService;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

	@Autowired	
	private CarService service;
	
	@GetMapping
	public ResponseEntity<Page<CarDTO>> findAll(Pageable pageable) {
		Page<CarDTO> page = service.findAll(pageable);
		return ResponseEntity.ok().body(page);
		}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
		CarDTO car = service.findById(id);
		return ResponseEntity.ok().body(car);
	}
	
	@PostMapping
	public ResponseEntity<CarDTO> insert(@RequestBody CarDTO dto) {
		CarDTO car = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(car.getId()).toUri();
		return ResponseEntity.created(uri).body(car);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarDTO dto) {
		CarDTO car = service.update(id, dto);
		return ResponseEntity.ok().body(car);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}