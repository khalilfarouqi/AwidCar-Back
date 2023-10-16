package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.dto.NotificationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Notification", description = "REST API for Notification information")
@RequestMapping("/v1/notification")
public interface NotificationApi {
    @PostMapping
    NotificationDto save(@RequestBody NotificationDto notification);
    @PutMapping
    NotificationDto update(@RequestBody NotificationDto notification);
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id") Long id);
    @GetMapping(value = "/{id}")
    NotificationDto getNotificationById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<NotificationDto> getAllNotification();
    @GetMapping(value = "/search")
    Page<NotificationDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
    @GetMapping(value = "/getByNotificationGroupId/{id}")
    List<NotificationDto> getByNotificationGroupId(@PathVariable("id") Long id);
    @GetMapping(value = "/getByClientId/{id}")
    List<NotificationDto> getByClientId(@PathVariable("id") Long id);
}
