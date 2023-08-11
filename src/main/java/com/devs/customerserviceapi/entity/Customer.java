package com.devs.customerserviceapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "public_id", unique = true, nullable = false)
    private long publicId;

    private String name;

    private String address;

    private double salary;

    @Column(columnDefinition = "TINYINT default 0")
    private boolean activeState;


}
