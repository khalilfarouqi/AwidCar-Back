package com.khalil.wdcar.entity;

import com.khalil.wdcar.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Brande")
    private Brande brande;

    @Column(name = "Mileage")
    private Double mileage;

    @Column(name = "Model")
    private int model;

    @Column(name = "HorsPower")
    private int horsPower;

    @Column(name = "Consumption")
    private Double consumption;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    private Fuel fuel;

    @Column(name = "Bluetooth")
    private Boolean bluetooth;

    @Column(name = "AirBag")
    private Boolean airBag;

    @Enumerated(EnumType.STRING)
    @Column(name = "GearBox")
    private GearBox gearBox;

    @Column(name = "Seat")
    private int seat;

    @Column(name = "Door")
    private int door;

    @Enumerated(EnumType.STRING)
    @Column(name = "Style")
    private Style style;

    @Column(name = "bootSize")
    private int bootSize;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Rate")
    private Double rate;

    //-------------------Assurence-------------------

    @Column(name = "CDM")
    private Boolean cdm;

    @Column(name = "ProtectionVol")
    private Boolean protectionVol;

    @Column(name = "RespoCivile")
    private Boolean respoCivile;

    @Column(name = "BrisGlacePneus")
    private Boolean brisGlacePneus;

    //-----------------------------------------------

    @OneToOne
    @JoinColumn(name = "immatriculation_id", unique = true)
    private Immatriculation immatriculation;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
