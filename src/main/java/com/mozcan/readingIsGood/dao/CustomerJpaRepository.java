package com.mozcan.readingIsGood.dao;

import com.mozcan.readingIsGood.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CustomerEntity c where c.email=?1")
    Boolean isUserExist(String email);
}
