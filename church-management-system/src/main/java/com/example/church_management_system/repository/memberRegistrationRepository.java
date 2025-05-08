package com.example.church_management_system.repository;

import com.example.church_management_system.Models.memberRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface memberRegistrationRepository extends JpaRepository<memberRegistration, Long> {
}