package com.example.elevatorsapplication.config;

import com.example.elevatorsapplication.model.Elevator;
import com.example.elevatorsapplication.repository.ElevatorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class DataInitializer implements CommandLineRunner {
    private final ElevatorRepository elevatorRepository;
    public DataInitializer(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra nếu bảng elevators trống
        if (elevatorRepository.count() == 0) {
            // Tạo 3 thang máy với trạng thái mặc định
            Elevator elevator1 = new Elevator();
            elevator1.setCurrentFloor(1);
            elevator1.setDirection("IDLE");

            Elevator elevator2 = new Elevator();
            elevator2.setCurrentFloor(1);
            elevator2.setDirection("IDLE");

            Elevator elevator3 = new Elevator();
            elevator3.setCurrentFloor(1);
            elevator3.setDirection("IDLE");

            // Lưu vào database
            elevatorRepository.save(elevator1);
            elevatorRepository.save(elevator2);
            elevatorRepository.save(elevator3);

            System.out.println("Initialized 3 elevators with default data.");
        }
    }
}
