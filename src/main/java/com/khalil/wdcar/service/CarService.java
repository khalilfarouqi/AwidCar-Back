package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.CarDto;
import com.khalil.wdcar.entity.Car;
import com.khalil.wdcar.exception.*;
import com.khalil.wdcar.repository.CarRepository;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CarService implements IBaseService<Car, CarDto> {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CarDto save(CarDto carDto) {
        return modelMapper.map(carRepository.save(modelMapper.map(carDto, Car.class)), CarDto.class);
    }

    @Override
    @Transactional
    public CarDto update(CarDto carDto) {
        if (findById(carDto.getId()).equals(null))
            throw new ResourceNotFoundException("car not fond");
        return modelMapper.map(carRepository.save(modelMapper.map(carDto, Car.class)), CarDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto findById(Long id) {
        CarDto carDto = modelMapper.map(carRepository.getById(id), CarDto.class);
        if (carDto == null) throw new InvalidInputException("Car not fond");
        return carDto;
    }

    @Override
    public List<CarDto> findAll() {
        return carRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private CarDto convertEntityToDto(Car car){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(car, CarDto.class);
    }

    @Override
    public Page<CarDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        if (query.isEmpty()) {
            throw new InvalidInputException("Argument is required");
        }
        if (size > 20) {
            size = 20;
        }
        return (Page<CarDto>) modelMapper.map(carRepository.findAll(RSQLJPASupport.toSpecification(query), PageRequest.of(page, size, Sort.Direction.fromString(order), sort)), CarDto.class);
    }
}
