package com.khalil.wdcar.rest.controller;

import com.khalil.wdcar.dto.PhotoDto;
import com.khalil.wdcar.rest.api.PhotoApi;
import com.khalil.wdcar.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PhotoController implements PhotoApi {
    private final PhotoService photoService;

    @Override
    public PhotoDto save(PhotoDto photo) {
        return photoService.save(photo);
    }

    @Override
    public PhotoDto update(PhotoDto photo) {
        return photoService.update(photo);
    }

    @Override
    public void delete(Long id) {
        photoService.delete(id);
    }

    @Override
    public PhotoDto getPhotoById(Long id) {
        return photoService.findById(id);
    }

    @Override
    public List<PhotoDto> getAllPhoto() {
        return photoService.findAll();
    }

    @Override
    public Page<PhotoDto> search(String query, Integer page, Integer size, String order, String sort) {
        return photoService.rsqlQuery(query, page, size, order, sort);
    }
    public Long getLastId() {
        return photoService.getLastId();
    }
}
