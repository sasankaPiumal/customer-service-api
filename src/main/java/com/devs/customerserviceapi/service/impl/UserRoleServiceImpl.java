package com.devs.customerserviceapi.service.impl;

import com.devs.customerserviceapi.entity.UserRole;
import com.devs.customerserviceapi.repo.UserRoleRepo;
import com.devs.customerserviceapi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }


    @Override
    public void initializeRoles() {

        List<UserRole> list;
        if (userRoleRepo.findAll().isEmpty()) {
            UserRole admin = new UserRole("POS-R-1", "ADMIN", "admin desc", null);
            UserRole user = new UserRole("POS-R-2", "USER", "user desc", null);
            UserRole manager = new UserRole("POS-R-3", "MANAGER", "manager desc", null);

            userRoleRepo.saveAll(List.of(admin, user, manager));
        }


    }
}
