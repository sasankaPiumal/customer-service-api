package com.devs.customerserviceapi.repo;

import com.devs.customerserviceapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Optional<Customer> findByPublicId(long id);

    void deleteByPublicId(long id);
}
