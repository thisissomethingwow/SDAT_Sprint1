package com.keyin.gate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends CrudRepository<Gate, Long> {
    List<Gate> findByAirportId(Long airportId);
}