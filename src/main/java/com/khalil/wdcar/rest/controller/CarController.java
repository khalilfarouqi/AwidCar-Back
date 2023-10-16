package com.khalil.wdcar.rest.controller;

import com.khalil.wdcar.dto.CarDto;
import com.khalil.wdcar.rest.api.CarApi;
import com.khalil.wdcar.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CarController implements CarApi {
    private final CarService carService;
    @Override
    public CarDto save(CarDto car) {
        return carService.save(car);
    }

    @Override
    public CarDto update(CarDto car) {
        return carService.update(car);
    }

    @Override
    public void delete(Long id) {
        carService.delete(id);
    }

    @Override
    public CarDto getCarById(Long id) {
        return carService.findById(id);
    }

    @Override
    public List<CarDto> getAllCar() {
        return carService.findAll();
    }

    @Override
    public Page<CarDto> search(String query, Integer page, Integer size, String order, String sort) {
        return carService.rsqlQuery(query, page, size, order, sort);
    }

    @Override
    public void deleteCar(Long id) {
        carService.delete(id);
    }
}
