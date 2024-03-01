package com.danna.purchase.mapper;

import com.danna.purchase.dto.CurrencyDTO;
import com.danna.purchase.model.Currency;

/**
 * @author Cristiane Danna
 */

public class CurrencyMapperImpl implements CurrencyMapper {

	@Override
	public Currency toEntity(CurrencyDTO currencyDTO) {
		Currency currency = new Currency();
		currency.setCode(currencyDTO.getCode());
		currency.setCountry(currencyDTO.getCountry());
		currency.setCurrency(currencyDTO.getCurrency());
		currency.setSymbol(currencyDTO.getSymbol());
		return currency;
	}

	@Override
	public CurrencyDTO toDTO(Currency currency) {
		CurrencyDTO currencyDTO = new CurrencyDTO();
		currencyDTO.setCode(currency.getCode());
		currencyDTO.setCountry(currency.getCountry());
		currencyDTO.setCurrency(currency.getCurrency());
		currencyDTO.setSymbol(currency.getSymbol());
		return currencyDTO;
	}

}
