package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.beans.RentalBean;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Search", description = "REST API for Search information")
@RequestMapping("/v1/search")
public interface SearchApi {
    @GetMapping(value = "/{label}")
    List<RentalBean> getSearch(@PathVariable("label") String label);
}
