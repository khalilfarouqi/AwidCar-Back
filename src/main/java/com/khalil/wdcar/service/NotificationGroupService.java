package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.NotificationGroupDto;
import com.khalil.wdcar.entity.NotificationGroup;
import com.khalil.wdcar.exception.*;
import com.khalil.wdcar.repository.NotificationGroupRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NotificationGroupService implements IBaseService<NotificationGroup, NotificationGroupDto> {
    @Autowired
    private NotificationGroupRepository notificationGroupRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public NotificationGroupDto save(NotificationGroupDto notificationGroupDto) {
        return modelMapper.map(notificationGroupRepository.save(modelMapper.map(notificationGroupDto, NotificationGroup.class)), NotificationGroupDto.class);
    }

    @Override
    @Transactional
    public NotificationGroupDto update(NotificationGroupDto notificationGroupDto) {
        Boolean exist = findById(notificationGroupDto.getId()).getNotificationWeb();
        if (exist == false) throw new ResourceNotFoundException("Notification Group not fond");
        return modelMapper.map(notificationGroupRepository.save(modelMapper.map(notificationGroupDto, NotificationGroup.class)), NotificationGroupDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notificationGroupRepository.deleteById(id);
    }

    @Override
    public NotificationGroupDto findById(Long id) {
        Optional<NotificationGroup> notificationGroupDto = notificationGroupRepository.findById(id);
        if (!notificationGroupDto.isPresent()) throw new InvalidInputException("notification Group not fond");
        return modelMapper.map(notificationGroupDto, NotificationGroupDto.class);
    }

    @Override
    public List<NotificationGroupDto> findAll() {
        return notificationGroupRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private NotificationGroupDto convertEntityToDto(NotificationGroup notificationGroup){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(notificationGroup, NotificationGroupDto.class);
    }

    @Override
    public Page<NotificationGroupDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        if (query.isEmpty()) {
            throw new InvalidInputException("Argument is required");
        }
        if (size > 20) {
            size = 20;
        }
        return (Page<NotificationGroupDto>) modelMapper.map(notificationGroupRepository.findAll(RSQLJPASupport.toSpecification(query), PageRequest.of(page, size, Sort.Direction.fromString(order), sort)), NotificationGroupDto.class);
    }
}
