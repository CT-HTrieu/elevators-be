package com.example.elevatorsapplication.repository;
import com.example.elevatorsapplication.model.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
}
