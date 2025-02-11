package com.example.elevatorsapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Elevator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int currentFloor;
    private String direction; // UP, DOWN, IDLE

    @ElementCollection
    @CollectionTable(name = "elevator_destinations", joinColumns = @JoinColumn(name = "elevator_id"))
    @Column(name = "destination_floor")
    private List<Integer> destinationFloors = new ArrayList<>();
}
