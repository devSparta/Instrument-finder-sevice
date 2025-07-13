package com.example.instrumentservice.controller;

import com.example.instrumentservice.model.Instrument;
import com.example.instrumentservice.repository.InstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/instruments")
@RequiredArgsConstructor

public class InstrumentController {

    private final InstrumentRepository instrumentRepository;

    @GetMapping
    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    @PostMapping
    public Instrument create(@RequestBody Instrument instrument) {
        return instrumentRepository.save(instrument);
    }
    
}


