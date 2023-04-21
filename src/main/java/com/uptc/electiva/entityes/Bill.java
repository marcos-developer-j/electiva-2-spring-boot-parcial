package com.uptc.electiva.entityes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "bills")
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private String number;
    
    @Column(nullable = false)
	private LocalDate date_bill;
    
    @Enumerated(EnumType.STRING)
	private TypePay type_bill;
    
    @Column(nullable = false)
    private Double total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
	private Customer customer;

	public Bill(String number, LocalDate date_bill, TypePay type_bill,Double total, Customer customer) {
		super();
		this.number = number;
		this.date_bill = date_bill;
		this.type_bill = type_bill;
		this.customer = customer;
		this.total=total;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getDate_bill() {
		return date_bill;
	}

	public void setDate_bill(LocalDate date_bill) {
		this.date_bill = date_bill;
	}

	public TypePay getType_bill() {
		return type_bill;
	}

	public void setType_bill(TypePay type_bill) {
		this.type_bill = type_bill;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Bill [number=" + number + ", date_bill=" + date_bill + ", type_bill=" + type_bill + ", customer="
				+ customer + "]";
	}
    
    
}


