package com.amsidh.mvc.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class PersonRequestModel implements Serializable {
    private String name;
    private Integer age;
    private AddressRequestModel addressRequestModel;
}
