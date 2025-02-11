package com.example.elevatorsapplication.scheduler;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;

import com.example.elevatorsapplication.business.service.ElevatorService;

@Service
public class ElevatorScheduler {

    private final ElevatorService elevatorService;

    public ElevatorScheduler (ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    // Simulate elevator movements every second
    @Scheduled(fixedRate = 2000)
    public void simulateElevatorMovements() {
        elevatorService.moveElevators();
    }
}
