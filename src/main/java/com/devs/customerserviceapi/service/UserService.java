package com.devs.customerserviceapi.service;

import com.devs.customerserviceapi.dto.request.RequestUserDto;

public interface UserService {

    public void createUser(RequestUserDto dto, String role);
}
