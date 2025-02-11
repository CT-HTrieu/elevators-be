package com.example.elevatorsapplication.business.service;

import com.example.elevatorsapplication.model.Elevator;
import com.example.elevatorsapplication.model.Request;

import com.example.elevatorsapplication.repository.ElevatorRepository;
import com.example.elevatorsapplication.repository.RequestRepository;
import java.util.Comparator;
import org.springframework.stereotype.Service;
import com.example.elevatorsapplication.config.RequestStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ElevatorService {

    private final ElevatorRepository elevatorRepository;

    private final RequestRepository requestRepository;


    public ElevatorService(ElevatorRepository elevatorRepository, RequestRepository requestRepository) {
        this.elevatorRepository = elevatorRepository;
        this.requestRepository = requestRepository;
    }
    // Get the status of all elevators
    public List<Elevator> getElevatorStatus() {
        return elevatorRepository.findAll();
    }

    public Request requestElevator(int floor, String direction) {
        // Find the nearest available elevator
        Elevator elevator = findNearestElevator(floor, direction);

        if (elevator == null) {
            throw new RuntimeException("No available elevator");
        }
        // Add the request to the elevator's destination list
        elevator.getDestinationFloors().add(floor);

        elevatorRepository.save(elevator);

        // Create a new request
        Request request = new Request();
        request.setFloor(floor);
        request.setDirection(direction);
        request.setStatus(RequestStatus.PENDING); // Set initial status
        request.setElevator(elevator);
        return requestRepository.save(request);

    }

    private Elevator findNearestElevator(int floor, String direction) {
        // Logic to find the nearest elevator
        return elevatorRepository.findAll().stream()
                .filter(e -> e.getDirection().equals("IDLE") || e.getDirection().equals(direction))
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - floor)))
                .orElseThrow(() -> new RuntimeException("No available elevator"));
    }

    @Transactional
    public void moveElevators() {
        // Logic to move elevators step by step
        List<Elevator> elevators = elevatorRepository.findAll();
        for (Elevator elevator : elevators) {
            if (elevator.getDestinationFloors().isEmpty()) {
                continue;
            }
            // Get the next floor
            int nextFloor = elevator.getDestinationFloors().get(0);
            elevator.setCurrentFloor(nextFloor); // Move the elevator to the next floor
            Integer remove = elevator.getDestinationFloors().removeFirst();// Remove the processed floor

            // Update the request status to COMPLETED
            List<Request> requests = requestRepository.findByFloorAndElevatorId(nextFloor, elevator.getId());
            for (Request request : requests) {
                request.setStatus(RequestStatus.COMPLETED);
                requestRepository.save(request);
            }

            elevatorRepository.save(elevator); // Save the updated state
        }
    }

    public void holdDoorOpen(Long elevatorId) {
        Elevator elevator = elevatorRepository.findById(elevatorId)
                .orElseThrow(() -> new RuntimeException("Elevator not found"));
        // Simulate holding the door open (e.g., delay for 10 seconds)
        System.out.println("Elevator " + elevatorId + " door held open for 10 seconds");
        try {
            Thread.sleep(10000); // Simulate 10 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void closeDoorImmediately(Long elevatorId) {
        Elevator elevator = elevatorRepository.findById(elevatorId)
                .orElseThrow(() -> new RuntimeException("Elevator not found"));
        // Simulate closing the door immediately
        System.out.println("Elevator " + elevatorId + " door closed immediately");
    }
}
