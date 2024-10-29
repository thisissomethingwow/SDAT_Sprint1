package com.keyin.flight;


import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface FlightRepository extends CrudRepository<Flight, Long>{
    @Query("SELECT DISTINCT f.departureAirport FROM Flight f JOIN f.passengers p WHERE p.id = :passengerId")
    List<Airport> findDepartureAirportsByPassenger(@Param("passengerId") Long passengerId);

    @Query("SELECT DISTINCT f.arrivalAirport FROM Flight f JOIN f.passengers p WHERE p.id = :passengerId")
    List<Airport> findArrivalAirportsByPassenger(@Param("passengerId") Long passengerId);


    @Query("SELECT DISTINCT f.aircraft FROM Flight f JOIN f.passengers p WHERE p.id = :passengerId")
    List<Aircraft> findAircraftByPassengerId(@Param("passengerId") Long passengerId);

}

