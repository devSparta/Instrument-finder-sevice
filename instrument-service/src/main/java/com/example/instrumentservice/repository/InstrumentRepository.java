package com.example.instrumentservice.repository;

import com.example.instrumentservice.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {}
