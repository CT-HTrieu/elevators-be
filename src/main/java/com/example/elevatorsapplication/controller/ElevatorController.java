package com.example.elevatorsapplication.controller;
import com.example.elevatorsapplication.model.Elevator;
import com.example.elevatorsapplication.model.Request;
import com.example.elevatorsapplication.business.service.ElevatorService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/elevator")
public class ElevatorController{

    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    // Call an elevator to a specific floor
    @PostMapping("/call")
    public Request callElevator(@RequestParam int floor, @RequestParam String direction) {
        return elevatorService.requestElevator(floor, direction);
    }


    // Get the status of all elevators
    @GetMapping("/status")
    public List<Elevator> getElevatorStatus() {
        return elevatorService.getElevatorStatus();
    }

    @PostMapping("/hold-door")
    public ResponseEntity<String> holdDoorOpen(@RequestParam Long elevatorId) {
        elevatorService.holdDoorOpen(elevatorId);
        return ResponseEntity.ok("Door held open");
    }

    @PostMapping("/close-door")
    public ResponseEntity<String> closeDoorImmediately(@RequestParam Long elevatorId) {
        elevatorService.closeDoorImmediately(elevatorId);
        return ResponseEntity.ok("Door closed immediately");
    }

    @PostMapping("/move-elevators")
    public ResponseEntity<String> moveElevators() {
        elevatorService.moveElevators();
        return ResponseEntity.ok("Elevators moved successfully");
    }
}
