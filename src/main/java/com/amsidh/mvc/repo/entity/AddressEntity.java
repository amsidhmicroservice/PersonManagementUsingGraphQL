package com.amsidh.mvc.repo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "personEntity")
@Builder
@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long addressId;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "state")
    private String state;
    @Column(name = "postal_code")
    private Long pinCode;
    @OneToOne
    @JoinColumn(name = "personId")
    private PersonEntity personEntity;
}
