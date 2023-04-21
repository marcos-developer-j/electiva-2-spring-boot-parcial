package com.uptc.electiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uptc.electiva.entityes.Customer;

@Repository
public interface RepositorieCustomer extends JpaRepository<Customer, Integer> {

}
