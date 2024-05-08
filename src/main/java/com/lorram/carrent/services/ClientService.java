package com.lorram.carrent.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.carrent.dto.ClientDTO;
import com.lorram.carrent.entities.Client;
import com.lorram.carrent.repositories.ClientRepository;
import com.lorram.carrent.services.exceptions.DatabaseException;
import com.lorram.carrent.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> list = repository.findAll(pageable);
		return list.map(x -> new ClientDTO(x));
	}
	
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client client = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ClientDTO(client);
	}
	
	public ClientDTO update(Long id, ClientDTO dto) {
		Client entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		try {
		fromDto(dto, entity);
		entity = repository.save(entity);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation"); 
		}
		return new ClientDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException("Integrity violation");
			}
	}
	
	private void fromDto(ClientDTO clientDto, Client entity) {
		entity.setName(clientDto.getName());
		entity.setEmail(clientDto.getEmail());
	}
}
