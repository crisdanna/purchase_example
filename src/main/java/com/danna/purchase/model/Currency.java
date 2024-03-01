package com.danna.purchase.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Cristiane Danna
 */

@Entity
@Table(name = "currency")
@Data
public class Currency {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String country;
	private String currency;
	private String symbol;
}
