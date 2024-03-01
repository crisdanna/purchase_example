package com.danna.purchase.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danna.purchase.dto.CurrencyDTO;
import com.danna.purchase.mapper.CurrencyMapper;
import com.danna.purchase.model.Currency;
import com.danna.purchase.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cristiane Danna
 */

@RestController
@RequestMapping("/currency")
@Slf4j
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyDTO> getCurrencyByCode(@PathVariable String code) {
        Currency currency = currencyService.getCurrencyByCode(code);
        if (currency != null) {
            CurrencyDTO dto = CurrencyMapper.INSTANCE.toDTO(currency);
            return ResponseEntity.ok(dto);
        } else {
        	log.debug("Currency not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<CurrencyDTO> getCurrencyByCountry(@PathVariable String country) {
        Currency currency = currencyService.getCurrencyByCountry(country);
        if (currency != null) {
            CurrencyDTO dto = CurrencyMapper.INSTANCE.toDTO(currency);
            return ResponseEntity.ok(dto);
        } else {
        	log.debug("Currency not found");
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        List<Currency> entities = currencyService.getAllCurrencies();
        List<CurrencyDTO> dtos = entities.stream()
                .map(CurrencyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
}
