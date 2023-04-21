package com.uptc.electiva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uptc.electiva.entityes.Bill;
import com.uptc.electiva.entityes.Customer;
import com.uptc.electiva.entityes.TypePay;
import com.uptc.electiva.repositories.RepositorieBill;
import com.uptc.electiva.repositories.RepositorieCustomer;

@Service
public class ServiceBill {
	
	private RepositorieBill repositoryBill;
	
	@Autowired
	private RepositorieCustomer repositoryCustomer;
	
    public ServiceBill(RepositorieBill repositoryBill) {
		this.repositoryBill = repositoryBill;
	}
	public List<Bill> findAll(){
        return repositoryBill.findAll();
    }
    public Bill findById(String id){
        Optional<Bill> bill = repositoryBill.findById(id);
        return bill.isPresent() ? bill.get() : null;
    }
    public List<Bill> findByTypePay(TypePay type_pay){
    	return repositoryBill.findByTypePay(type_pay);
    }
    
    public Bill save( Bill bill, Integer id ){
        Customer customer = repositoryCustomer.findById( id ).get();
        bill.setCustomer( customer );
        return repositoryBill.save( bill );
    }
    public Bill update( Bill bill){
        return repositoryBill.save( bill );
    }
    public void deleteById(String id) {
    	repositoryBill.deleteById(id);;
    }
}
