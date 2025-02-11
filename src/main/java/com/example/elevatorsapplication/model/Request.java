package com.example.elevatorsapplication.model;
import jakarta.persistence.*;
import lombok.Data;
import com.example.elevatorsapplication.config.RequestStatus;
@Entity
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floor;
    private String direction; // UP, DOWN
    @ManyToOne
    @JoinColumn(name = "elevator_id")
    private Elevator elevator;

    @Enumerated(EnumType.STRING)
    private RequestStatus status; // "PENDING", "PROCESSING", "COMPLETED"
}
