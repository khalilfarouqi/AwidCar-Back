package com.khalil.wdcar.service;

import com.khalil.wdcar.dto.ImmatriculationDto;
import com.khalil.wdcar.entity.Immatriculation;
import com.khalil.wdcar.exception.InvalidInputException;
import com.khalil.wdcar.exception.ResourceNotFoundException;
import com.khalil.wdcar.repository.ImmatriculationRepository;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ImmatriculationService implements IBaseService<Immatriculation, ImmatriculationDto> {
    @Autowired
    private ImmatriculationRepository immatriculationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ImmatriculationDto save(ImmatriculationDto immatriculationDto) {
        if (!existsImmatriculation(immatriculationDto))
            return modelMapper.map(immatriculationRepository.save(modelMapper.map(immatriculationDto, Immatriculation.class)), ImmatriculationDto.class);
        else
            throw new InvalidInputException("This matriculation exists before");
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
        Optional<Immatriculation> immatriculationDto = immatriculationRepository.findById(id);
        if (!immatriculationDto.isPresent()) throw new InvalidInputException("Immatriculation not fond");
        return modelMapper.map(immatriculationDto, ImmatriculationDto.class);
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

    public Long getLastId() {
        return immatriculationRepository.getLastId().get(0);
    }

    public boolean existsImmatriculation(ImmatriculationDto immatriculationDto){
        return immatriculationRepository.existsByCarNumberAndSeriesAndPrefectureRefId(immatriculationDto.getCarNumber(), immatriculationDto.getSeries(), immatriculationDto.getPrefectureRef().getId());
    }
}
