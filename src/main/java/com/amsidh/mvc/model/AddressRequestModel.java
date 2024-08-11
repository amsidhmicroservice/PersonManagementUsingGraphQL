package com.amsidh.mvc.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class AddressRequestModel implements Serializable {
    private String city;
    private String street;
    private String state;
    private Long pinCode;
}
