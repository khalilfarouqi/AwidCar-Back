package com.khalil.wdcar.dto;

import com.khalil.wdcar.entity.enums.Series;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImmatriculationDto implements Serializable {
    private Long id;
    private int carNumber;
    private Series series;

    private PrefectureRefDto prefectureRef;
}
