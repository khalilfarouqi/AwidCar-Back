package com.khalil.wdcar.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Photo1")
    private String photo1;

    @Column(name = "Photo2")
    private String photo2;

    @Column(name = "Photo3")
    private String photo3;

    @Column(name = "Photo4")
    private String photo4;
}
