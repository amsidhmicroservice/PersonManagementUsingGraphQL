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
    private String firstName;
    private String lastName;
    private String email;
    private AddressRequestModel addressRequestModel;
}
