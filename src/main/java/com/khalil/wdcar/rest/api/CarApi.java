package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.beans.CarRentalBean;
import com.khalil.wdcar.dto.CarDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Car", description = "REST API for Car information")
@RequestMapping("/v1/car")
public interface CarApi {
    @PostMapping
    CarDto save(@RequestBody CarDto car);
    @PutMapping
    CarDto update(@RequestBody CarDto car);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    CarDto getCarById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<CarDto> getAllCar();
    @GetMapping(value = "/search")
    Page<CarDto> search(@RequestParam(defaultValue = "id>0") String query,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "asc") String order,
                       @RequestParam(defaultValue = "id") String sort);
    @GetMapping("/delete/{id}")
    void deleteCar(@PathVariable Long id);
    @GetMapping(value = "/findAllByQuery")
    List<CarDto> findAllByQuery(@RequestBody CarRentalBean carRentalBean);
}
