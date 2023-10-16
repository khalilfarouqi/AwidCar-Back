package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.PhotoDto;
import com.khalil.wdcar.entity.Photo;
import com.khalil.wdcar.exception.InvalidInputException;
import com.khalil.wdcar.exception.ResourceNotFoundException;
import com.khalil.wdcar.repository.*;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PhotoService implements IBaseService<Photo, PhotoDto> {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Transactional
    public PhotoDto save(PhotoDto photoDto){
        return modelMapper.map(photoRepository.save(modelMapper.map(photoDto, Photo.class)), PhotoDto.class);
    }

    @Override
    @Transactional
    public PhotoDto update(PhotoDto photoDto) {
        if (findById(photoDto.getId()).equals(null))
            throw new ResourceNotFoundException("photo not fond");
        return modelMapper.map(photoRepository.save(modelMapper.map(photoDto, Photo.class)), PhotoDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public PhotoDto findById(Long id) {
        PhotoDto photoDto = modelMapper.map(photoRepository.findById(id).get(), PhotoDto.class);
        if (photoDto == null) throw new InvalidInputException("Photo not fond");
        return photoDto;
    }

    private PhotoDto convertEntityToDto(Photo photo){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(photo, PhotoDto.class);
    }

    @Override
    public List<PhotoDto> findAll() {
        return photoRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Page<PhotoDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        if (query.isEmpty()) {
            throw new InvalidInputException("Argument is required");
        }
        if (size > 20) {
            size = 20;
        }
        return (Page<PhotoDto>) modelMapper.map(photoRepository.findAll(RSQLJPASupport.toSpecification(query), PageRequest.of(page, size, Sort.Direction.fromString(order), sort)), PhotoDto.class);
    }
}
