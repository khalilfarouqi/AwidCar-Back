package com.khalil.wdcar.service;

import com.khalil.wdcar.beans.CarRentalBean;
import com.khalil.wdcar.dto.CarDto;
import com.khalil.wdcar.entity.Car;
import com.khalil.wdcar.exception.*;
import com.khalil.wdcar.repository.CarRepository;
import io.github.perplexhub.rsql.RSQLJPASupport;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private final EntityManager entityManager;

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
        Optional<Car> carDto = carRepository.findById(id);
        if (!carDto.isPresent()) throw new InvalidInputException("Car not fond");
        return modelMapper.map(carDto, CarDto.class);
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
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(order), sort);
        Specification<Car> a = RSQLJPASupport.toSpecification(query);
        Page<Car> cars = carRepository.findAll(a, pageable);
        Page<CarDto> carDtos = (Page<CarDto>) modelMapper.map(cars, CarDto.class);
        return carDtos;
    }
    public List<CarDto> findAllByQuery(CarRentalBean carRentalBean){
        List<CarDto> carDtoList = new ArrayList<>();
        List<Car> carList = entityManager.createNativeQuery("select * from cars where " + carRentalBean.toQuery("car"), Car.class).getResultList();
        for (Car car : carList)
            carDtoList.add(modelMapper.map(car, CarDto.class));
        return carDtoList;
    }
}
