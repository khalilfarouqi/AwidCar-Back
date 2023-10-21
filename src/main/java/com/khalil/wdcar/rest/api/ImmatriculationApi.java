package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.dto.ImmatriculationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Immatriculation", description = "REST API for Immatriculation information")
@RequestMapping("/v1/immatriculation")
public interface ImmatriculationApi {
    @PostMapping
    ImmatriculationDto save(@RequestBody ImmatriculationDto immatriculation);
    @PutMapping
    ImmatriculationDto update(@RequestBody ImmatriculationDto immatriculation);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    ImmatriculationDto getImmatriculationById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<ImmatriculationDto> getAllImmatriculation();
    @GetMapping(value = "/search")
    Page<ImmatriculationDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
    @GetMapping("/delete/{id}")
    void deleteImmatriculation(@PathVariable Long id);
}
