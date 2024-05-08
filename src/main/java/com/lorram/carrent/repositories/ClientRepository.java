package com.lorram.carrent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.carrent.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
