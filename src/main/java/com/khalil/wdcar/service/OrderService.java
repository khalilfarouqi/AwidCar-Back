package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.OrderDto;
import com.khalil.wdcar.entity.Order;
import com.khalil.wdcar.exception.*;
import com.khalil.wdcar.repository.OrderRepository;
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
public class OrderService implements IBaseService<Order, OrderDto> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        return modelMapper.map(orderRepository.save(modelMapper.map(orderDto, Order.class)), OrderDto.class);
    }

    @Override
    @Transactional
    public OrderDto update(OrderDto orderDto) {
        if (findById(orderDto.getId()).equals(null))
            throw new ResourceNotFoundException("Order not fond");
        return modelMapper.map(orderRepository.save(modelMapper.map(orderDto, Order.class)), OrderDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto findById(Long id) {
        OrderDto orderDto = modelMapper.map(orderRepository.findById(id).get(), OrderDto.class);
        if (orderDto == null) throw new InvalidInputException("Order not fond");
        return orderDto;
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private OrderDto convertEntityToDto(Order order){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Page<OrderDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        if (query.isEmpty()) {
            throw new InvalidInputException("Argument is required");
        }
        if (size > 20) {
            size = 20;
        }
        return (Page<OrderDto>) modelMapper.map(orderRepository.findAll(RSQLJPASupport.toSpecification(query), PageRequest.of(page, size, Sort.Direction.fromString(order), sort)), OrderDto.class);
    }
}
