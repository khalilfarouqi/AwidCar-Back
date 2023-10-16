package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.dto.NotificationGroupDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "NotificationGroup", description = "REST API for NotificationGroup information")
@RequestMapping("/v1/notificationGroup")
public interface NotificationGroupApi {
    @PostMapping
    NotificationGroupDto save(@RequestBody NotificationGroupDto notificationGroup);
    @PutMapping
    NotificationGroupDto update(@RequestBody NotificationGroupDto notificationGroup);
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id") Long id);
    @GetMapping(value = "/{id}")
    NotificationGroupDto getNotificationGroupById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<NotificationGroupDto> getAllNotificationGroup();
    @GetMapping(value = "/search")
    Page<NotificationGroupDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
