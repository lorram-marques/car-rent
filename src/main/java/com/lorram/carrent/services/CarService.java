package com.lorram.carrent.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.carrent.dto.CarDTO;
import com.lorram.carrent.entities.Car;
import com.lorram.carrent.repositories.CarRepository;
import com.lorram.carrent.services.exceptions.DatabaseException;
import com.lorram.carrent.services.exceptions.ResourceNotFoundException;

@Service
public class CarService {
	@Autowired
	private CarRepository repository;
	
	public Page<CarDTO> findAll(Pageable pageable) {
		Page<Car> list = repository.findAll(pageable);
		return list.map(x -> new CarDTO(x));
	}
	
	public CarDTO findById(Long id) {
		Optional<Car> obj = repository.findById(id);
		Car car = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new CarDTO(car);
	}
	
	public CarDTO update(Long id, CarDTO dto) {
		Car entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new CarDTO(entity);
	}

	public CarDTO insert(CarDTO dto) {
		Car entity = new Car();
		try {
		fromDto(dto, entity);
		entity = repository.save(entity);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation"); 
		}
		return new CarDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException("Integrity violation");
			}
	}
	
	private void fromDto(CarDTO carDto, Car entity) {
		entity.setModel(carDto.getModel());
	}
}
