package com.example.church_management_system.repository;

import com.example.church_management_system.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository  <Admin, Long >{

    Admin findByUsername(String username);
}
