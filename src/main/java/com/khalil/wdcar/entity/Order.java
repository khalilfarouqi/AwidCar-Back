package com.khalil.wdcar.entity;

import com.khalil.wdcar.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Etat")
    private Etat etat;

    @Column(name = "Country")
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "BookingCity")
    private City bookingCity;

    @Enumerated(EnumType.STRING)
    @Column(name = "ReturnCity")
    private City returnCity;

    @DateTimeFormat(pattern = "DD/MM/YYYY")
    @Column(name = "start_date")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "DD/MM/YYYY")
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "TypeInsurance")
    private String typeInsurance;

    @Column(name = "OrderStatus")
    private String orderStatus;

    @Column(name = "Numbre_Days_Reserved")
    private int numbreDaysReserved;

    @Column(name = "Total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;
}
