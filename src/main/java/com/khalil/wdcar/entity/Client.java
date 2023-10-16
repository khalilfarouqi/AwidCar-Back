package com.khalil.wdcar.entity;

import com.khalil.wdcar.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "LastCheckIn")
    private Date lastCheckIn;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "PassWord")
    private String passWord;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;

    @Column(name = "Age")
    private int age;

    @Column(name = "Adress")
    private String adress;

    @Column(name = "Tel")
    private String tel;

    @Enumerated(EnumType.STRING)
    @Column(name = "City")
    private City city;

    @Column(name = "Country")
    private String country;

    @Column(name = "CIN")
    private String cin;

    @Column(name = "Licence")
    private String licence;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Date_licence")
    private Date dateLicence;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Date_creation")
    private Date dateCreation;

    @Column(name = "Email")
    private String email;

    @Column(name = "VolNumber")
    private int volNumber;

    @Column(name = "Image")
    private String image;
}
