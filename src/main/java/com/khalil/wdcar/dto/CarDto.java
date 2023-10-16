package com.khalil.wdcar.dto;

import com.khalil.wdcar.entity.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto implements Serializable {
    private Long id;
    private String name;
    private Brande brande;
    private Double mileage;
    private int model;
    private int horsPower;
    private Double consumption;
    private Fuel fuel;
    private Boolean bluetooth;
    private Boolean airBag;
    private GearBox gearBox;
    private int seat;
    private int door;
    private Style style;
    private int bootSize;
    private Double price;
    private Double rate;
    private Boolean cdm;
    private Boolean protectionVol;
    private Boolean respoCivile;
    private Boolean brisGlacePneus;
    private ImmatriculationDto immatriculation;
    private PhotoDto photo;
}
