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

import com.lorram.carrent.dto.RentLogDTO;
import com.lorram.carrent.services.RentLogService;

@RestController
@RequestMapping(value = "/rentlogs")
public class RentLogController {

	@Autowired	
	private RentLogService service;
	
	@GetMapping
	public ResponseEntity<Page<RentLogDTO>> findAll(Pageable pageable) {
		Page<RentLogDTO> page = service.findAll(pageable);
		return ResponseEntity.ok().body(page);
		}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RentLogDTO> findById(@PathVariable Long id) {
		RentLogDTO rentLog = service.findById(id);
		return ResponseEntity.ok().body(rentLog);
	}
	
	@PostMapping
	public ResponseEntity<RentLogDTO> insert(@RequestBody RentLogDTO dto) {
		RentLogDTO rentLog = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rentLog.getId()).toUri();
		return ResponseEntity.created(uri).body(rentLog);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RentLogDTO> update(@PathVariable Long id, @RequestBody RentLogDTO dto) {
		RentLogDTO rentLog = service.update(id, dto);
		return ResponseEntity.ok().body(rentLog);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}