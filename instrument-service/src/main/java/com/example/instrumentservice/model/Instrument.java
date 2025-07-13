package com.example.instrumentservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "instrument")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Музыкальный инструмент")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор инструмента", example = "10")
    private Long id;

    @Schema(description = "Тип инструмента", example = "Гитара")
    private String type;

    @Schema(description = "Бренд", example = "Fender")
    private String brand;

    @Schema(description = "Материал", example = "Дерево")
    private String material;

    @Schema(description = "Цена", example = "499.99")
    private Double price;

    @Schema(description = "ID поставщика", example = "1")
    private Long supplierId;
}
