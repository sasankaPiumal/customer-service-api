package com.devs.customerserviceapi.repo;

import com.devs.customerserviceapi.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRoleHasUserRepo extends JpaRepository<UserRoleHasUser,String> {
}
