package com.uptc.electiva.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uptc.electiva.entityes.Bill;
import com.uptc.electiva.entityes.TypePay;

@Repository
public interface RepositorieBill extends JpaRepository<Bill, String> {
	List<Bill> findByTypePay(TypePay typePay);
}
