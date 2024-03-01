package com.danna.purchase.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Cristiane Danna
 */

@Entity
@Data
public class Purchase {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String description;

    @NotNull
    private LocalDate purchaseDate;

    @NotNull
    @Positive
    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal purchaseAmountUSD;
    
}
