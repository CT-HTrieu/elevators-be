package com.example.elevatorsapplication.repository;
import com.example.elevatorsapplication.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r WHERE r.floor = :floor AND r.elevator.id = :elevatorId")
    List<Request> findByFloorAndElevatorId(@Param("floor") int floor, @Param("elevatorId") Long elevatorId);
}