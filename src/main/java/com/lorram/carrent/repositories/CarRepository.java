package com.lorram.carrent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.carrent.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
