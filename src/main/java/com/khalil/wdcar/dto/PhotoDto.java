package com.khalil.wdcar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoDto implements Serializable {
    private Long id;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
}
