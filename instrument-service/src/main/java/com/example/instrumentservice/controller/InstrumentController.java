package com.example.instrumentservice.controller;

import com.example.instrumentservice.client.SupplierClient;
import com.example.instrumentservice.model.Instrument;
import com.example.instrumentservice.model.Supplier;
import com.example.instrumentservice.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;
    private final SupplierClient supplierClient;  // 🔧 добавлен заново — он используется

    @GetMapping
    public List<Instrument> findAll() {
        return instrumentService.findAll();
    }

    @PostMapping
    public Instrument create(@RequestBody Instrument instrument) {
        return instrumentService.save(instrument);
    }

    @GetMapping("/search")
    public List<Instrument> searchInstruments(
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "") String brand,
            @RequestParam(defaultValue = "") String material,
            @RequestParam(defaultValue = "0") BigDecimal minPrice,
            @RequestParam(defaultValue = "23000000") BigDecimal maxPrice,
            @RequestParam(required = false) String supplierName
    ) {
        if (supplierName != null && !supplierName.isBlank()) {
            List<Supplier> suppliers = supplierClient.getAllSuppliers();

            return suppliers.stream()
                    .filter(s -> s.getName().equalsIgnoreCase(supplierName))
                    .findFirst()
                    .map(supplier -> instrumentService.searchWithSupplier(
                            type, brand, material, minPrice, maxPrice, supplier.getId()))
                    .orElse(List.of());
        } else {
            return instrumentService.search(type, brand, material, minPrice, maxPrice);
        }
    }
}
