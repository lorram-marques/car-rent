package com.lorram.carrent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.carrent.entities.RentLog;

@Repository
public interface RentLogRepository extends JpaRepository<RentLog, Long>{

}
