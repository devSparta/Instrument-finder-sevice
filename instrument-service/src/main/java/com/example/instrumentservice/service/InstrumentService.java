package com.example.instrumentservice.service;

import com.example.instrumentservice.model.Instrument;

import java.math.BigDecimal;
import java.util.List;

public interface InstrumentService {
    List<Instrument> findAll();
    Instrument save(Instrument instrument);

    List<Instrument> search(String type, String brand, String material, BigDecimal minPrice, BigDecimal maxPrice);

    List<Instrument> searchWithSupplier(String type, String brand, String material, BigDecimal minPrice, BigDecimal maxPrice, Long supplierId);
}
