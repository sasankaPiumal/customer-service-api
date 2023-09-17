package com.devs.customerserviceapi.service.impl;

import com.devs.customerserviceapi.dto.core.UserDto;
import com.devs.customerserviceapi.dto.request.RequestUserDto;
import com.devs.customerserviceapi.entity.User;
import com.devs.customerserviceapi.entity.UserRoleHasUser;
import com.devs.customerserviceapi.exception.DuplicateEntryException;
import com.devs.customerserviceapi.exception.EntryNotFoundException;
import com.devs.customerserviceapi.repo.UserRepo;
import com.devs.customerserviceapi.repo.UserRoleHasUserRepo;
import com.devs.customerserviceapi.repo.UserRoleRepo;
import com.devs.customerserviceapi.service.UserService;
import com.devs.customerserviceapi.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final UserRoleRepo userRoleRepo;

    private final UserMapper userMapper;

    private final UserRoleHasUserRepo userRoleHasUserRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, UserMapper userMapper, UserRoleHasUserRepo userRoleHasUserRepo) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userMapper = userMapper;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
    }


    @Override
    public void createUser(RequestUserDto dto, String role) {

      var selectedUserRole= userRoleRepo.findByRoleNameEquals(role);
      if (selectedUserRole.isEmpty()){
          throw new EntryNotFoundException("User role not found!");
      }

      var selectedUser = userRepo.findUserByEmail(dto.getEmail());
      if (selectedUser.isPresent()){
          throw new DuplicateEntryException("User email already exists.");
      }

        UserDto userDto = new UserDto(
                String.valueOf(new Random().nextInt(10012)),
                dto.getEmail(),
                dto.getFullName(),
                dto.getPassword()
        );


        User user = userMapper.toUser(userDto);
        userRepo.save(user);         // userRepo.save(userMapper.toUser(userDto));
        UserRoleHasUser userRoleHasUser = new UserRoleHasUser();
        userRoleHasUser.setUser(user);
        userRoleHasUser.setUserRole(selectedUserRole.get());
        userRoleHasUserRepo.save(userRoleHasUser);






    }

}
