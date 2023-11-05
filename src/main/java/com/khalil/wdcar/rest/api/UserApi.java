package com.khalil.wdcar.rest.api;

import com.khalil.wdcar.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "User", description = "REST API for User information")
@RequestMapping("/v1/user")
public interface UserApi {
    @PostMapping
    UserDto save(@RequestBody UserDto user);
    @PutMapping
    UserDto update(@RequestBody UserDto user);
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id") Long id);
    @GetMapping(value = "/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<UserDto> getAllUser();
    @GetMapping(value = "/search")
    Page<UserDto> search(@RequestParam(defaultValue = "id>0") String query,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "asc") String order,
                       @RequestParam(defaultValue = "id") String sort);
    @GetMapping(value = "existPassword/{id}/{currentPassword}")
    Boolean existsPassWord(@PathVariable("id") Long id, @PathVariable("currentPassword") String currentPassword);
}
