package com.devs.customerserviceapi.repo;

import com.devs.customerserviceapi.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRoleHasUserRepo extends JpaRepository<UserRoleHasUser,String> {

    @Query(value = "SELECT * FROM user_role_has_user WHERE user_id=?1",nativeQuery = true)
    public List<UserRoleHasUser> findByUserId(String userId);
}
