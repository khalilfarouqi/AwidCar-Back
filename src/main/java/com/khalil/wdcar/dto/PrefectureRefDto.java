package com.khalil.wdcar.dto;

import com.khalil.wdcar.entity.enums.Prefecture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrefectureRefDto implements Serializable {
    private Long id;
    private int code;
    private Prefecture prefecture;

    @JsonIgnore
    private List<ImmatriculationDto> immatriculationsDtos;
}
