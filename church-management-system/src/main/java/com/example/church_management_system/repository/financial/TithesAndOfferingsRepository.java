package com.example.church_management_system.repository.financial;

import com.example.church_management_system.Models.financial.TithesAndOfferings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TithesAndOfferingsRepository extends JpaRepository <TithesAndOfferings, Long > {
}
