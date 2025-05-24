package com.example.church_management_system.service.financial;

import com.example.church_management_system.Models.financial.TithesAndOfferings;

import java.util.List;
import java.util.Optional;

public interface TithesAndOfferingService {
    TithesAndOfferings saveTithesAndOfferings(TithesAndOfferings tithesAndOfferings);
    List<TithesAndOfferings> getAllTithesAndOfferings();
    Optional<TithesAndOfferings> getTithesAndOfferingsById(Long id);
    TithesAndOfferings updateTithesAndOfferings(Long id, TithesAndOfferings tithesAndOfferings);
    void deleteTithesAndOfferings(Long id);
}