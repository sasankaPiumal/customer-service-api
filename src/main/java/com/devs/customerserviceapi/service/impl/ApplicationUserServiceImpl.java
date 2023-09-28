package com.devs.customerserviceapi.service.impl;

import com.devs.customerserviceapi.entity.UserRole;
import com.devs.customerserviceapi.entity.UserRoleHasUser;
import com.devs.customerserviceapi.repo.UserRepo;
import com.devs.customerserviceapi.repo.UserRoleHasUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.devs.customerserviceapi.security.ApplicationUserRole.USER;

public class ApplicationUserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    private final UserRoleHasUserRepo userRoleHasUserRepo;

    @Autowired
    public ApplicationUserServiceImpl(UserRepo userRepo, UserRoleHasUserRepo userRoleHasUserRepo) {
        this.userRepo = userRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var selectedUser = userRepo.findUserByEmail(username);
        if (selectedUser.isEmpty()){
            throw new UsernameNotFoundException("User Name %s Not Found!."+username);
        }

        List<UserRoleHasUser> userRoles = userRoleHasUserRepo.findByUserId(selectedUser.get().getUserId());
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRoleHasUser uRole : userRoles) {
            if (uRole.getUserRole().equals("USER")){
                grantedAuthorities.addAll(USER.getSimpleGrantedAuthorities());
            }
            if (uRole.getUserRole().equals("ADMIN")){
                grantedAuthorities.addAll(USER.getSimpleGrantedAuthorities());
            }
            if (uRole.getUserRole().equals("MANAGER")){
                grantedAuthorities.addAll(USER.getSimpleGrantedAuthorities());
            }
        }

        ApplicationUser
    }
}
