package com.uptc.electiva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uptc.electiva.response.ResponseHandler;
import com.uptc.electiva.entityes.Customer;
import com.uptc.electiva.services.ServiceCustomer;

@RestController
@RequestMapping(value = "/customers")
public class ControllerCustomer {
	private ServiceCustomer serviceCustomer;
		
	public ControllerCustomer(ServiceCustomer serviceCustomer) {
		this.serviceCustomer = serviceCustomer;
	}
	
    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Customer> result = serviceCustomer.findAll();
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,result);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Customer customer ){
        try{
            Customer result = serviceCustomer.save( customer );
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,result);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try{
            Customer result = serviceCustomer.findById( id );
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,result);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @GetMapping("/totalBills/{id}")
    public ResponseEntity<Object> getTotalBillsByCustomerId(@PathVariable Integer id){
        try{
            Customer result = serviceCustomer.findById( id );
            Double total = result.getSumBills();
            String out = "El total de las facturas para " + result.getName() +" es de "+ total;
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,out);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> UpdateById(@RequestBody Customer customer ,@PathVariable Integer id){
        try{
            Customer updatedCustomer  = serviceCustomer.findById( id );
            if(updatedCustomer !=null) {
            	if(customer.getName()!=null) {
            		updatedCustomer.setName(customer.getName());
            	};
            	if(customer.getBirthday()!=null) {
            		updatedCustomer.setBirthday(customer.getBirthday());
            	};
            	if(customer.getGender()!=null) {
            		updatedCustomer.setGender(customer.getGender());
            	};
            	if(customer.getBills()!=null) {
            		updatedCustomer.setBills(customer.getBills());
            	};
            }
            Customer result = serviceCustomer.update(updatedCustomer);
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,updatedCustomer);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id){
        try{
            serviceCustomer.deleteById( id );
            String message = "Borrado con exito";
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,message);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
	

}
