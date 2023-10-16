package com.khalil.wdcar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    private Long id;
    private Date lastCheckIn;
    private String userName;
    private String passWord;
}
