package com.danna.purchase.service;

import java.util.List;

import com.danna.purchase.model.ExchangeRate;

import lombok.Data;

/**
 * @author Cristiane Danna
 */

@Data
public class ExchangeRateResponse {

	private List<ExchangeRate> data;
}
