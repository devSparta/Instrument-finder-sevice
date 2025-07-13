package com.example.instrumentservice.model;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class Supplier {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private String name;
    private String country;
}
