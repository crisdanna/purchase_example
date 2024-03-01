package com.danna.purchase.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danna.purchase.model.Purchase;
import com.danna.purchase.repository.PurchaseRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cristiane Danna
 */

@Service
@Slf4j
public class PurchaseService {
	
	@Autowired
    private PurchaseRepository purchaseRepository;
	
	public void savePurchase(Purchase purchase) {
		purchaseRepository.save(purchase);
	}
	
	public Purchase findPurchaseById(Long id) {
		return purchaseRepository.findById(id).orElse(null);
	}
	
	public List<Purchase> findAll() {
		return purchaseRepository.findAll();
	}

	public void delete(Long id) {
		purchaseRepository.deleteById(id);
	}

	public BigDecimal getConvertedPurchaseAmount(BigDecimal purchaseAmountUSD, LocalDate purchaseDate, String country) {
		double exchangeRate = getExchangeRate(country, purchaseDate);
		if (exchangeRate <= 0) {
			// Handle error when exchange rate is not available within 6 months
			throw new RuntimeException(
					"No exchange rate available within 6 months for country: " + country);
		}
		
		BigDecimal exchangeRateObject = new BigDecimal(exchangeRate);

		return purchaseAmountUSD.multiply(exchangeRateObject).setScale(2,
					RoundingMode.HALF_UP);

	}

	// Date format is YYYY-MM-DD
	private double getExchangeRate(String country, LocalDate transactionDate) {
		// Call Treasury Reporting Rates of Exchange API to get exchange rate
		// Construct URL based on country and date
		String url = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=record_date:gte:"
				+ transactionDate.minusMonths(6)
				+ ",country:eq:"
				+ country
				+ "&sort=-record_date&fields=record_date,country,currency,exchange_rate";
			
		// Make HTTP request to Treasury Reporting Rates of Exchange API
		// Parse response to get exchange rate
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<ExchangeRateResponse> responseEntity = restTemplate.getForEntity(url, ExchangeRateResponse.class);

		ExchangeRateResponse exchangeRateResponse = null;
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
        	exchangeRateResponse = responseEntity.getBody();
            // Process the Person object
            log.info("Received exchange rate response: {}", exchangeRateResponse.toString());
        } else {
            // Handle error response
            log.error("Failed to fetch data. Status code: {}", responseEntity.getStatusCodeValue());
        }

		// Return first exchange rate, which is the most recent one
		return exchangeRateResponse.getData().getFirst().getExchange_rate();
	}

}
