package com.uptc.electiva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uptc.electiva.entityes.Customer;
import com.uptc.electiva.repositories.RepositorieCustomer;


@Service
public class ServiceCustomer {
	private RepositorieCustomer repositoryCustomer;
	
	
    public ServiceCustomer(RepositorieCustomer repositoryCustomer) {
		super();
		this.repositoryCustomer = repositoryCustomer;
	}

	public List<Customer> findAll(){
        return repositoryCustomer.findAll();
    }

    public Customer findById( Integer id ){
        Optional<Customer> customer = repositoryCustomer.findById( id );
        return customer.isPresent() ? customer.get() : null;
    }

    public void deleteById( Integer id ){
        repositoryCustomer.deleteById(id);
    }
    public Customer save( Customer customer ){
        return repositoryCustomer.save( customer );
    }
    public Customer update( Customer customer ){
        return repositoryCustomer.save( customer );
    }

}
