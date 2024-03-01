package com.danna.purchase.model;

import java.util.Date;

import lombok.Data;

/**
 * @author Cristiane Danna
 */

@Data
public class ExchangeRate {
	private Date record_date;
	private String country;
	private String currency;
	private double exchange_rate;
}
