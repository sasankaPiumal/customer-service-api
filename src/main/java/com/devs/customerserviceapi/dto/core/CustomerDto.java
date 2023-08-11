package com.devs.customerserviceapi.dto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private long id;
    private long publicId;
    private String name;
    private String address;
    private double salary;
    private boolean activeState;
}
