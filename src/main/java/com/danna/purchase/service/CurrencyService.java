package com.danna.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danna.purchase.model.Currency;
import com.danna.purchase.repository.CurrencyRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cristiane Danna
 */

@Service
@Slf4j
public class CurrencyService {

	@Autowired
	private CurrencyRepository repository;
	
	public Currency getCurrencyByCode(String code) {
		return repository.findByCode(code);
	}
	
	public Currency getCurrencyByCountry(String country) {
		return repository.findByCountry(country);
	}
	
	public List<Currency> getAllCurrencies() {
		List<Currency> results = repository.findAll();
		log.info("Got {} results from db.", results.size());
		return results;
	}
}
