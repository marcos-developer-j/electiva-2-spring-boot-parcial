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

import com.uptc.electiva.entityes.Bill;
import com.uptc.electiva.entityes.Customer;
import com.uptc.electiva.entityes.TypePay;
import com.uptc.electiva.response.ResponseHandler;
import com.uptc.electiva.services.ServiceBill;
import com.uptc.electiva.services.ServiceCustomer;

@RestController
@RequestMapping(value = "/bills")
public class ControllerBill {
	private ServiceBill serviceBill;
	
	@Autowired
	private ServiceCustomer serviceCustomer;
	
	public ControllerBill(ServiceBill serviceBill) {
		this.serviceBill = serviceBill;
	}
	
    @GetMapping
    public ResponseEntity<Object> getBills(){
        try{
            List<Bill> result = serviceBill.findAll();
            return new ResponseHandler().generateResponse("Success Completed!",HttpStatus.OK,result);
        }catch( Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByTypePay(@PathVariable String id){
        try{
            Bill result = serviceBill.findById( id );
            return new ResponseHandler().generateResponse("Success Completed!",HttpStatus.OK,result);
        }catch( Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @GetMapping("/type-pay/{type_pay}")
    public ResponseEntity<Object> findByTypePay(@PathVariable TypePay type_pay){
        try{
            List<Bill> result = serviceBill.findByTypePay( type_pay );
            return new ResponseHandler().generateResponse("Success Completed!",HttpStatus.OK,result);
        }catch( Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @PostMapping("/{id_customer}")
    public ResponseEntity<Object> save( @RequestBody Bill bill, @PathVariable Integer id_customer ){
        if( serviceCustomer.findById( id_customer ) != null ){
            try{
            	Bill result = serviceBill.save( bill, id_customer);
                return new ResponseHandler().generateResponse("Success Completed!",HttpStatus.OK,result);
            }catch(Exception e){
                return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
            }
        }
        return new ResponseHandler().generateResponse("Fail Author not Found", HttpStatus.NOT_ACCEPTABLE,null);

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> UpdateById(@RequestBody Bill bill ,@PathVariable String id){
        try{
            Bill updatedBill  = serviceBill.findById( id );
            if(updatedBill !=null) {
            	if(bill.getDate_bill()!=null) {
            		updatedBill.setDate_bill(bill.getDate_bill());
            	};
            	if(bill.getTotal()!=null) {
            		updatedBill.setTotal(bill.getTotal());
            	};
            	if(bill.getType_bill()!=null) {
            		updatedBill.setType_bill(bill.getType_bill());
            	};
            	if(bill.getCustomer()!=null) {
            		updatedBill.setCustomer(bill.getCustomer());
            	}
            }
            Bill result = serviceBill.update(updatedBill);
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,updatedBill);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id){
        try{
            serviceBill.deleteById( id );
            String message = "Borrado con exito";
            return new ResponseHandler().generateResponse("Success Completed!", HttpStatus.OK,message);
        }catch(Exception e ){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
