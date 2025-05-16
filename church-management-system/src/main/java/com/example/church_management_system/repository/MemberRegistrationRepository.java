package com.example.church_management_system.repository;

import com.example.church_management_system.Models.MemberRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRegistrationRepository extends JpaRepository<MemberRegistration, Long> {

    Optional<MemberRegistration> findByEmail(String email);
}