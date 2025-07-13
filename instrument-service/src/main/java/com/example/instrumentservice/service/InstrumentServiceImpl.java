package com.example.instrumentservice.service;

import com.example.instrumentservice.model.Instrument;
import com.example.instrumentservice.repository.InstrumentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    @Override
    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @Override
    public List<Instrument> search(String type, String brand, String material, BigDecimal minPrice, BigDecimal maxPrice) {
        return instrumentRepository.findByTypeContainingIgnoreCaseAndBrandContainingIgnoreCaseAndMaterialContainingIgnoreCaseAndPriceBetween(
                type, brand, material, minPrice, maxPrice
        );
    }

    @Override
    public List<Instrument> searchWithSupplier(String type, String brand, String material, BigDecimal minPrice, BigDecimal maxPrice, Long supplierId) {
        return instrumentRepository.findByTypeContainingIgnoreCaseAndBrandContainingIgnoreCaseAndMaterialContainingIgnoreCaseAndPriceBetweenAndSupplierId(
                type, brand, material, minPrice, maxPrice, supplierId
        );
    }
}
