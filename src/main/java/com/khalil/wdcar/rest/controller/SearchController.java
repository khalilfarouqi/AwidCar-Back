package com.khalil.wdcar.rest.controller;

import com.khalil.wdcar.beans.RentalBean;
import com.khalil.wdcar.rest.api.SearchApi;
import com.khalil.wdcar.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SearchController implements SearchApi {
    public final SearchService searchService;
    public List<RentalBean> getSearch(String label) {
        return searchService.getSearch(label);
    }
}
