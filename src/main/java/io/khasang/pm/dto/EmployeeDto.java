package io.khasang.pm.dto;

import io.khasang.pm.entity.Car;
import io.khasang.pm.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeeDto {
    private Long id;

    private String name;
    private String title;
    private List<CarDto> carDtoList = new ArrayList<>();

    public EmployeeDto getEmployeeDto(Employee employee) {
        List<CarDto> carDtos = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setId(employee.getId());
        employeeDto.setTitle(employee.getTitle());
        HashSet et;
        LinkedList l;
        Stack s;
        TreeSet t;

        getCarDtoFromCar(employee, carDtos);

        employeeDto.setCarDtoList(carDtos);
        return employeeDto;
    }

    private void getCarDtoFromCar(Employee employee, List<CarDto> carDtos) {
        for (Car car : employee.getCarList()) {
            CarDto carDto = new CarDto();
            carDto.setId(car.getId());
            carDto.setModel(car.getModel());
            carDto.setYear(car.getYear());

            carDtos.add(carDto);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CarDto> getCarList() {
        return carDtoList;
    }

    public void setCarDtoList(List<CarDto> carList) {
        this.carDtoList = carList;
    }
}
