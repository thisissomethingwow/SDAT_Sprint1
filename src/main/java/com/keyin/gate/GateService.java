package com.keyin.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateService {
    @Autowired
    private GateRepository gateRepository;

    public List<Gate> getAllGate() {
        return (List<Gate>) gateRepository.findAll();
    }

    public Gate getGateById(Long id) {
        return gateRepository.findById(id).orElse(null);
    }

    public Gate addGate(Gate gate) {
        return gateRepository.save(gate);
    }

    public void deleteGateById(Long id) {
        gateRepository.deleteById(id);
    }

    public Gate updateGate(long id, Gate updatedGate) {
        Optional<Gate> gateToUpdateOptional = gateRepository.findById(id);

        if (gateToUpdateOptional.isPresent()) {
            Gate gateToUpdate = gateToUpdateOptional.get();
            gateToUpdate.setGateNumber(updatedGate.getGateNumber());
            gateToUpdate.setStatus(updatedGate.getStatus());
            gateToUpdate.setTerminal(updatedGate.getTerminal());
            gateToUpdate.setAirport(updatedGate.getAirport());
//            gateToUpdate.setFlights(updatedGate.getFlights());
            return gateRepository.save(gateToUpdate);
        }
        return null;
    }
    public List<Gate> getGatesByAirport(Long airportId) {
        return gateRepository.findByAirportId(airportId);
    }


}