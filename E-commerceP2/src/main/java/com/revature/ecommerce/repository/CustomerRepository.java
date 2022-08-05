package com.revature.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select c from Customer c where c.firstname like %:firstName%")
	List<Customer>findByFirstName(@Param(value="firstName") String firstName);
	
	@Query("select c from Customer c where c.userName like %:userName%")
	List<Customer>findByUserName(@Param(value="userName") String userName);
	
	

}
