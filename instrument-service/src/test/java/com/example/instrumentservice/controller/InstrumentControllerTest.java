package com.example.instrumentservice.controller;

import com.example.instrumentservice.client.SupplierClient;
import com.example.instrumentservice.model.Instrument;
import com.example.instrumentservice.service.InstrumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InstrumentController.class)


public class InstrumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierClient supplierClient;

    @MockBean
    private InstrumentService instrumentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAllReturnsEmptyList() throws Exception {
        Mockito.when(instrumentService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/instruments"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testSearchInstruments() throws Exception {
        Instrument instrument = new Instrument();
        instrument.setId(1L);
        instrument.setType("Guitar");
        instrument.setBrand("Yamaha");
        instrument.setMaterial("Wood");
        instrument.setPrice(1000.0);

        Mockito.when(instrumentService.search("Guitar", "Yamaha", "Wood",
                        new BigDecimal("0"), new BigDecimal("10000")))
                .thenReturn(List.of(instrument));

        mockMvc.perform(get("/api/instruments/search")
                        .param("type", "Guitar")
                        .param("brand", "Yamaha")
                        .param("material", "Wood")
                        .param("minPrice", "0")
                        .param("maxPrice", "10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Guitar"))
                .andExpect(jsonPath("$[0].brand").value("Yamaha"))
                .andExpect(jsonPath("$[0].material").value("Wood"))
                .andExpect(jsonPath("$[0].price").value(1000.0));
    }

    @Test
    void testCreateInstrument() throws Exception {
        Instrument instrument = new Instrument();
        instrument.setId(1L);
        instrument.setType("Piano");
        instrument.setBrand("Casio");
        instrument.setMaterial("Plastic");
        instrument.setPrice(2000.0);

        Mockito.when(instrumentService.save(Mockito.any(Instrument.class))).thenReturn(instrument);

        mockMvc.perform(post("/api/instruments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(instrument)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Piano"))
                .andExpect(jsonPath("$.brand").value("Casio"))
                .andExpect(jsonPath("$.material").value("Plastic"))
                .andExpect(jsonPath("$.price").value(2000.0));
    }

    @Test
    void testSearchInstrumentsWithInvalidPriceRange() throws Exception {
        mockMvc.perform(get("/api/instruments/search")
                        .param("type", "Violin")
                        .param("brand", "")
                        .param("material", "")
                        .param("minPrice", "abc")
                        .param("maxPrice", "10000"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSearchInstrumentsNoMatch() throws Exception {
        Mockito.when(instrumentService.search("Drum", "", "", new BigDecimal("0"), new BigDecimal("5000")))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/instruments/search")
                        .param("type", "Drum")
                        .param("minPrice", "0")
                        .param("maxPrice", "5000"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testSearchInstrumentsWithEmptyBrandAndMaterial() throws Exception {
        Instrument instrument = new Instrument();
        instrument.setId(2L);
        instrument.setType("Flute");
        instrument.setBrand("");
        instrument.setMaterial("");
        instrument.setPrice(300.0);

        Mockito.when(instrumentService.search("Flute", "", "", new BigDecimal("0"), new BigDecimal("500")))
                .thenReturn(List.of(instrument));

        mockMvc.perform(get("/api/instruments/search")
                        .param("type", "Flute")
                        .param("brand", "")
                        .param("material", "")
                        .param("minPrice", "0")
                        .param("maxPrice", "500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Flute"));
    }

    @Test
    void testSearchInstrumentsWithExtremeMinPrice() throws Exception {
        Mockito.when(instrumentService.search("Saxophone", "Yamaha", "Metal",
                        new BigDecimal("1000000"), new BigDecimal("2000000")))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/instruments/search")
                        .param("type", "Saxophone")
                        .param("brand", "Yamaha")
                        .param("material", "Metal")
                        .param("minPrice", "1000000")
                        .param("maxPrice", "2000000"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
