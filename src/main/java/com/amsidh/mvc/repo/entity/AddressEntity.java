package com.amsidh.mvc.repo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String city;
    private String street;
    private String state;
    private Long pinCode;
    @OneToOne
    @JoinColumn(name = "personId")
    private PersonEntity personEntity;
}
