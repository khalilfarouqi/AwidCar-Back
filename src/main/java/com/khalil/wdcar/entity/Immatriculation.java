package com.khalil.wdcar.entity;

import com.khalil.wdcar.entity.enums.Series;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Immatriculations")
public class Immatriculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Number")
    private int carNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "Series")
    private Series series;

    @ManyToOne
    @JoinColumn(name="prefecture_ref_id", nullable = false)
    private PrefectureRef prefectureRef;

}
