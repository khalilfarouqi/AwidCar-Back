package com.khalil.wdcar.entity;

import com.khalil.wdcar.entity.enums.Prefecture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="PrefectureRefs")
public class PrefectureRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Code")
    private int code;

    @Enumerated(EnumType.STRING)
    @Column(name = "Prefecture")
    private Prefecture prefecture;

    @OneToMany(mappedBy = "prefectureRef", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Immatriculation> immatriculations;

}
