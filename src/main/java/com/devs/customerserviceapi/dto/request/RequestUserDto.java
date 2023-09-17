package com.devs.customerserviceapi.dto.request;

import lombok.*;

import javax.persistence.Column;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {

    private String email;

    private String fullName;

    private String password;

}
