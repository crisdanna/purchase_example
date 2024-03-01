package com.danna.purchase.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Cristiane Danna
 */

@Data
public class PurchaseDTO {
	
	@NotBlank
    @Size(max = 50)
    private String description;

    @NotNull
    private LocalDate purchaseDate;

    @NotNull
    @Positive
    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal purchaseAmountUSD;
    
    private BigDecimal purchaseAmountConverted;
    	
}
