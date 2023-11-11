package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.UserDto;
import com.khalil.wdcar.entity.User;
import com.khalil.wdcar.exception.*;
import com.khalil.wdcar.repository.*;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements IBaseService<User, UserDto> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        userDto.setLastCheckIn(new Date());
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        if (findById(userDto.getId()).equals(null))
            throw new ResourceNotFoundException("user not fond");
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> userDto = userRepository.findById(id);
        if (!userDto.isPresent()) throw new InvalidInputException("User not fond");
        return modelMapper.map(userDto, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Page<UserDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        if (query.isEmpty()) {
            throw new InvalidInputException("Argument is required");
        }
        if (size > 20) {
            size = 20;
        }
        return (Page<UserDto>) modelMapper.map(userRepository.findAll(RSQLJPASupport.toSpecification(query), PageRequest.of(page, size, Sort.Direction.fromString(order), sort)), UserDto.class);
    }

    public Boolean existsPassWord(Long id, String currentPassword) {
        return userRepository.existsByIdAndPassWord(id, currentPassword);
    }
}
