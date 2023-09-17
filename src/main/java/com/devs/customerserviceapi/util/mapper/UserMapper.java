package com.devs.customerserviceapi.util.mapper;

import com.devs.customerserviceapi.dto.core.UserDto;
import com.devs.customerserviceapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

}
