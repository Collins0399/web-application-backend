package com.example.church_management_system.service.impl.financial;

import com.example.church_management_system.Models.financial.TithesAndOfferings;
import com.example.church_management_system.service.financial.TithesAndOfferingService;
import com.example.church_management_system.repository.financial.TithesAndOfferingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TithesAndOfferingsImpl implements TithesAndOfferingService {

    @Autowired
    private TithesAndOfferingsRepository tithesAndOfferingsRepository;

    @Override
    public TithesAndOfferings saveTithesAndOfferings(TithesAndOfferings tithesAndOfferings) {
        return tithesAndOfferingsRepository.save(tithesAndOfferings);
    }

    @Override
    public List<TithesAndOfferings> getAllTithesAndOfferings() {
        return tithesAndOfferingsRepository.findAll();
    }

    @Override
    public Optional<TithesAndOfferings> getTithesAndOfferingsById(Long id) {
        return tithesAndOfferingsRepository.findById(id);
    }

    @Override
    public TithesAndOfferings updateTithesAndOfferings(Long id, TithesAndOfferings tithesAndOfferings) {
        return tithesAndOfferingsRepository.findById(id).map(existingRecord -> {
            updateExistingRecord(existingRecord, tithesAndOfferings);
            return tithesAndOfferingsRepository.save(existingRecord);
        }).orElseThrow(() -> new RuntimeException("TithesAndOfferings not found with id: " + id));
    }

    @Override
    public void deleteTithesAndOfferings(Long id) {
        if (!tithesAndOfferingsRepository.existsById(id)) {
            throw new RuntimeException("TithesAndOfferings not found with id: " + id);
        }
        tithesAndOfferingsRepository.deleteById(id);
    }

    private void updateExistingRecord(TithesAndOfferings existingRecord, TithesAndOfferings newRecord) {

        existingRecord.setAmount(newRecord.getAmount());
        existingRecord.setDateOfContribution(newRecord.getDateOfContribution());
    }
}