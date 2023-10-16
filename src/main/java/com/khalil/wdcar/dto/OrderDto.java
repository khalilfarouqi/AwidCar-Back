package com.khalil.wdcar.dto;

import com.khalil.wdcar.entity.enums.City;
import com.khalil.wdcar.entity.enums.Etat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto implements Serializable {
    private Long id;
    private Etat etat;
    private String country;
    private City bookingCity;
    private City returnCity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String typeInsurance;
    private String orderStatus;
    private int numbreDaysReserved;
    private double total;
    private CarDto car;
    private ClientDto client;
}
