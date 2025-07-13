package com.example.instrumentservice.client;

import com.example.instrumentservice.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "supplier-service", url = "http://localhost:8081/api/suppliers")
public interface SupplierClient {

    @GetMapping
    List<Supplier> getAllSuppliers();

    @PostMapping
    Supplier createSupplier(@RequestBody Supplier supplier);

    @GetMapping("/{id}")
    Supplier getSupplierById(@PathVariable("id") Long id);

}
