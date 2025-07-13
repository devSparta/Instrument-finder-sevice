package com.example.instrumentservice.repository;

import com.example.instrumentservice.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    List<Instrument> findByTypeContainingIgnoreCaseAndBrandContainingIgnoreCaseAndMaterialContainingIgnoreCaseAndPriceBetween(
            String type,
            String brand,
            String material,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<Instrument> findByTypeContainingIgnoreCaseAndBrandContainingIgnoreCaseAndMaterialContainingIgnoreCaseAndPriceBetweenAndSupplierId(
            String type,
            String brand,
            String material,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Long supplierId
    );
}
