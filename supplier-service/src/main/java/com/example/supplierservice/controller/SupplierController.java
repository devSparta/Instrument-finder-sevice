package com.example.supplierservice.controller;

import com.example.supplierservice.model.Supplier;
import com.example.supplierservice.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierRepository supplierRepository;

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
