package com.amsidh.mvc.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class PersonResponseModel implements Serializable {
    private Long personId;
    private String name;
    private Integer age;
    private String city;
    private String street;
    private String state;
    private Long pinCode;
}
