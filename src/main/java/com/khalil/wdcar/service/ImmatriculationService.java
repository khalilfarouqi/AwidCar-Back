package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.ImmatriculationDto;
import com.khalil.wdcar.entity.Immatriculation;
import com.khalil.wdcar.exception.InvalidInputException;
import com.khalil.wdcar.exception.ResourceNotFoundException;
import com.khalil.wdcar.repository.ImmatriculationRepository;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class ImmatriculationService implements IBaseService<Immatriculation, ImmatriculationDto> {
    @Autowired
    private ImmatriculationRepository immatriculationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ImmatriculationDto save(ImmatriculationDto immatriculationDto) {
        return modelMapper.map(immatriculationRepository.save(modelMapper.map(immatriculationDto, Immatriculation.class)), ImmatriculationDto.class);
    }

    @Override
    @Transactional
    public ImmatriculationDto update(ImmatriculationDto immatriculationDto) {
        if (findById(immatriculationDto.getId()).equals(null))
            throw new ResourceNotFoundException("Immatriculation not fond");
        return modelMapper.map(immatriculationRepository.save(modelMapper.map(immatriculationDto, Immatriculation.class)), ImmatriculationDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        immatriculationRepository.deleteById(id);
    }

    @Override
    public ImmatriculationDto findById(Long id) {
        ImmatriculationDto immatriculationDto = modelMapper.map(immatriculationRepository.getById(id), ImmatriculationDto.class);
        if (immatriculationDto == null) throw new InvalidInputException("Immatriculation not fond");
        return immatriculationDto;
    }

    @Override
    public List<ImmatriculationDto> findAll() {
        return immatriculationRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private ImmatriculationDto convertEntityToDto(Immatriculation immatriculation){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(immatriculation, ImmatriculationDto.class);
    }

    @Override
    public Page<ImmatriculationDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        if (query.isEmpty()) {
            throw new InvalidInputException("Argument is required");
        }
        if (size > 20) {
            size = 20;
        }
        return (Page<ImmatriculationDto>) modelMapper.map(immatriculationRepository.findAll(RSQLJPASupport.toSpecification(query), PageRequest.of(page, size, Sort.Direction.fromString(order), sort)), ImmatriculationDto.class);
    }
}
