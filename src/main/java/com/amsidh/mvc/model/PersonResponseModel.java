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
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponseModel addressResponseModel;
}
