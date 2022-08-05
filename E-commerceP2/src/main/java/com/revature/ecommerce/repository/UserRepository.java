package com.revature.ecommerce.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.model.Users;

@Repository
public interface UserRepository  extends JpaRepository<Users, Long>{
    
    @Query("select u from Users u where u.email like %:email%")
    List<Users> findByEmail(@Param("email")String Email);
  
}