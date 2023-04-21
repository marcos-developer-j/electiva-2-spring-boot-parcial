package com.uptc.electiva.entityes;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "customers")
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idCustomer;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private LocalDate birthday;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@OneToMany(mappedBy = "customer")
	private List<Bill> bills;
	public Customer() {
		super();
	}
	public Customer(int idCustomer, String name, LocalDate birthday, Gender gender) {
		super();
		this.idCustomer = idCustomer;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		bills = new ArrayList<>();
	}
    public Bill findBook( Bill bill ){
        Optional< Bill > optBill = bills.stream()
                .filter( b -> bill.getNumber() == b.getNumber() )
                .findFirst();

        return optBill.isPresent() ? optBill.get() : null;
    }
    public boolean addBook( Bill bill ){
        if ( findBook( bill ) == null ){
        	bills.add( bill );
            return true;
        }
        return false;
    }
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public Double getSumBills() {
		double result = 0;
		for (Bill bill : this.bills) {
			result+= bill.getTotal();
		}
		return result;
		
	}
	
	
}


