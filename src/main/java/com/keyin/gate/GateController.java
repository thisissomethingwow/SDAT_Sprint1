package com.keyin.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gate")
@CrossOrigin
public class GateController {
    @Autowired
    public GateService gateService;

    @GetMapping
    public List<Gate> getAllGate() {
        return gateService.getAllGate();
    }

    @GetMapping("/{id}")
    public Gate getGateById(@PathVariable Long id) {
        return gateService.getGateById(id);
    }

    @PostMapping
    public Gate addGate(@RequestBody Gate gate) {
        return gateService.addGate(gate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gate> updateGate(@PathVariable long id, @RequestBody Gate gate) {
        return ResponseEntity.ok(gateService.updateGate(id, gate));
    }

    @DeleteMapping("/{id}")
    public void deleteGate(@PathVariable long id) {
        gateService.deleteGateById(id);
    }
}