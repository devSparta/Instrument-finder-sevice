package com.example.instrumentservice.controller;

import com.example.instrumentservice.client.SupplierClient;
import com.example.instrumentservice.model.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proxy/suppliers")
@RequiredArgsConstructor
public class SupplierProxyController {

    private final SupplierClient supplierClient;

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierClient.getAllSuppliers();
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierClient.createSupplier(supplier);
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierClient.getSupplierById(id);
    }
}
