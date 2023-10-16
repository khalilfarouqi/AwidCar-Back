package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.NotificationDto;
import com.khalil.wdcar.entity.Notification;
import com.khalil.wdcar.exception.*;
import com.khalil.wdcar.repository.NotificationRepository;
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
public class NotificationService implements IBaseService<Notification, NotificationDto> {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
    public NotificationDto save(NotificationDto notificationDto) {
        return modelMapper.map(notificationRepository.save(modelMapper.map(notificationDto, Notification.class)), NotificationDto.class);
    }

    @Override
    @Transactional
    public NotificationDto update(NotificationDto notificationDto) {
        if (findById(notificationDto.getId()).equals(null))
            throw new ResourceNotFoundException("notification not fond");
        return modelMapper.map(notificationRepository.save(modelMapper.map(notificationDto, Notification.class)), NotificationDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public NotificationDto findById(Long id) {
        NotificationDto notificationDto = modelMapper.map(notificationRepository.findById(id).get(), NotificationDto.class);
        if (notificationDto == null) throw new InvalidInputException("Notification not fond");
        return notificationDto;
    }

    @Override
    public List<NotificationDto> findAll() {
        return notificationRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private NotificationDto convertEntityToDto(Notification notification){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(notification, NotificationDto.class);
    }

    @Override
    public Page<NotificationDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        if (query.isEmpty()) {
            throw new InvalidInputException("Argument is required");
        }
        if (size > 20) {
            size = 20;
        }
        return (Page<NotificationDto>) modelMapper.map(notificationRepository.findAll(RSQLJPASupport.toSpecification(query), PageRequest.of(page, size, Sort.Direction.fromString(order), sort)), NotificationDto.class);
    }

    public List<NotificationDto> getByNotificationGroupId(Long id) {
        return notificationRepository.getByNotificationGroupId(id).stream().map((element) -> modelMapper.map(element, NotificationDto.class)).collect(Collectors.toList());
    }

    public List<NotificationDto> getByClientId(Long id) {
        return notificationRepository.getByClientId(id).stream().map((element) -> modelMapper.map(element, NotificationDto.class)).collect(Collectors.toList());
    }
}
