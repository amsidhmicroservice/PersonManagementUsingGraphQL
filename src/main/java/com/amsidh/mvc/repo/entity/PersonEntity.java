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
@Table(name = "PERSON")
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;
    private String name;
    private Integer age;

    @OneToOne(mappedBy = "personEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity addressEntity;


}
