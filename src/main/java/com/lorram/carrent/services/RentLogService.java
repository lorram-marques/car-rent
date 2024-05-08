package com.lorram.carrent.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.carrent.dto.RentLogDTO;
import com.lorram.carrent.entities.RentLog;
import com.lorram.carrent.repositories.CarRepository;
import com.lorram.carrent.repositories.ClientRepository;
import com.lorram.carrent.repositories.RentLogRepository;
import com.lorram.carrent.services.exceptions.DatabaseException;
import com.lorram.carrent.services.exceptions.ResourceNotFoundException;



@Service
public class RentLogService {

	@Autowired
	private RentLogRepository repository;
	
	@Autowired 
	private CarRepository carRepository;
	
	@Autowired 
	private ClientRepository clientRepository;
	
	public Page<RentLogDTO> findAll(Pageable pageable) {
		Page<RentLog> list = repository.findAll(pageable);
		return list.map(x -> new RentLogDTO(x));
	}
	
	public RentLogDTO findById(Long id) {
		Optional<RentLog> obj = repository.findById(id);
		RentLog rentLog = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new RentLogDTO(rentLog);
	}
	
	public RentLogDTO update(Long id, RentLogDTO dto) {
		RentLog entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new RentLogDTO(entity);
	}

	public RentLogDTO insert(RentLogDTO dto) {
		RentLog entity = new RentLog();
		try {
		fromDto(dto, entity);
		entity = repository.save(entity);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation"); 
		}
		return new RentLogDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException("Integrity violation");
			}
	}
	
	private void fromDto(RentLogDTO rentLogDto, RentLog entity) {
		entity.setRentDate(rentLogDto.getRentDate());
		entity.setReturnDate(rentLogDto.getReturnDate());
		entity.setCar(carRepository.getReferenceById(rentLogDto.getCarId()));
		entity.setClient(clientRepository.getReferenceById(rentLogDto.getCarId()));
	}
}
