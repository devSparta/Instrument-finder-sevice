package com.example.supplierservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
@Schema(description = "Поставщик")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Название поставщика", example = "Yamaha")
    private String name;

    @Schema(description = "Страна", example = "Japan")
    private String country;
}
