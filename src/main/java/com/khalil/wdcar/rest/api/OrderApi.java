package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.dto.OrderDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Order", description = "REST API for Order information")
@RequestMapping("/v1/order")
public interface OrderApi {
    @PostMapping
    OrderDto save(@RequestBody OrderDto order);
    @PutMapping
    OrderDto update(@RequestBody OrderDto order);
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id") Long id);
    @GetMapping(value = "/{id}")
    OrderDto getOrderById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<OrderDto> getAllOrder();
    @GetMapping(value = "/search")
    Page<OrderDto> search(@RequestParam(defaultValue = "id>0") String query,
                      @RequestParam(defaultValue = "0") Integer page,
                      @RequestParam(defaultValue = "10") Integer size,
                      @RequestParam(defaultValue = "asc") String order,
                      @RequestParam(defaultValue = "id") String sort);
}
