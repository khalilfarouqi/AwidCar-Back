package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.dto.PhotoDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Photo", description = "REST API for Photo information")
@RequestMapping("/v1/photo")
public interface PhotoApi {
    @PostMapping
    PhotoDto save(@RequestBody PhotoDto photo);
    @PutMapping
    PhotoDto update(@RequestBody PhotoDto photo);
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id") Long id);
    @GetMapping(value = "/{id}")
    PhotoDto getPhotoById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<PhotoDto> getAllPhoto();
    @GetMapping(value = "/search")
    Page<PhotoDto> search(@RequestParam(defaultValue = "id>0") String query,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "asc") String order,
                       @RequestParam(defaultValue = "id") String sort);
}
