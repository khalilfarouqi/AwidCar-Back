package com.khalil.wdcar.rest.controller;

import com.khalil.wdcar.dto.ImmatriculationDto;
import com.khalil.wdcar.rest.api.ImmatriculationApi;
import com.khalil.wdcar.service.ImmatriculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ImmatriculationController implements ImmatriculationApi {
    private final ImmatriculationService immatriculationService;
    @Override
    public ImmatriculationDto save(ImmatriculationDto immatriculation) {
        return immatriculationService.save(immatriculation);
    }

    @Override
    public ImmatriculationDto update(ImmatriculationDto immatriculation) {
        return immatriculationService.update(immatriculation);
    }

    @Override
    public void delete(Long id) {
        immatriculationService.delete(id);
    }

    @Override
    public ImmatriculationDto getImmatriculationById(Long id) {
        return immatriculationService.findById(id);
    }

    @Override
    public List<ImmatriculationDto> getAllImmatriculation() {
        return immatriculationService.findAll();
    }

    @Override
    public Page<ImmatriculationDto> search(String query, Integer page, Integer size, String order, String sort) {
        return immatriculationService.rsqlQuery(query, page, size, order, sort);
    }

    @Override
    public void deleteImmatriculation(Long id) {
        immatriculationService.delete(id);
    }
}
