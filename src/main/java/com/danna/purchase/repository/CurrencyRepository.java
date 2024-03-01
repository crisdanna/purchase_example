package com.danna.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danna.purchase.model.Currency;

/**
 * @author Cristiane Danna
 */

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String>{
	
	public Currency findByCountry(String country);

	public Currency findByCode(String code);
}
