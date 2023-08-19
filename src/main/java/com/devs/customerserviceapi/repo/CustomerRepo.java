package com.devs.customerserviceapi.repo;

import com.devs.customerserviceapi.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByPublicId(long id);

    void deleteByPublicId(long id);

    //Page<Customer> searchAllByNameOrAddress(String searchText, PageRequest of);

    @Query(value = "SELECT * FROM customer WHERE name LIKE %?1% OR address_id LIKE %?1%", nativeQuery = true)
    Page<Customer> searchAllByNameOrAddressId(String searchText, Pageable pageRequest);

    @Query(value = "SELECT COUNT(id)  FROM customer WHERE name LIKE %?1% OR address_id LIKE %?1%", nativeQuery = true)
    long CountDataSearchText(String searchText);
}
