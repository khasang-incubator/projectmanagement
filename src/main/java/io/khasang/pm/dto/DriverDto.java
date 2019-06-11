package io.khasang.pm.dto;

import io.khasang.pm.entity.Car;
import io.khasang.pm.entity.Driver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DriverDto {
    private long id;
    private String name;
    private List<CarDto> carsDtoList = new ArrayList<>();

    public DriverDto getDriverDto(Driver driver) {
        List<CarDto> carDtos = new ArrayList<>();
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setName(driver.getName());

        getCarDtoFromCar(driver, carDtos);
        driverDto.setCarsDtoList(carDtos);
        return driverDto;
    }

    private void getCarDtoFromCar(Driver driver, List<CarDto> carDtos) {
        for (Car car : driver.getCars()) {
                CarDto carDto = new CarDto();
                carDto.setId(car.getId());
                carDto.setModel(car.getModel());
                carDto.setNumber(car.getNumber());
                carDtos.add(carDto);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarDto> getCarsDtoList() {
        return carsDtoList;
    }

    public void setCarsDtoList(List<CarDto> carsDtoList) {
        this.carsDtoList = carsDtoList;
    }
}
