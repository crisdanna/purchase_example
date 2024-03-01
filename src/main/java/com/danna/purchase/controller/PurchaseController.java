package com.danna.purchase.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danna.purchase.dto.PurchaseDTO;
import com.danna.purchase.mapper.PurchaseMapper;
import com.danna.purchase.model.Purchase;
import com.danna.purchase.service.PurchaseService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Cristiane Danna
 */

@RestController
@RequestMapping("/purchase")
@Slf4j
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Void> createPurchase(@Valid @RequestBody PurchaseDTO purchaseTransactionDto) {
    	log.debug("Create purchase");
        Purchase entity = PurchaseMapper.INSTANCE.toEntity(purchaseTransactionDto);
        purchaseService.savePurchase(entity);
        log.debug("Purchase created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseService.findPurchaseById(id);
        if (purchase != null) {
            PurchaseDTO dto = PurchaseMapper.INSTANCE.toDTO(purchase);
            return ResponseEntity.ok(dto);
        } else {
        	log.debug("Purchase not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        List<Purchase> entities = purchaseService.findAll();
        List<PurchaseDTO> dtos = entities.stream()
                .map(PurchaseMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    
    @GetMapping("/{id}/{country}")
    public ResponseEntity<PurchaseDTO> getPurchaseWithConvertedAmount(@PathVariable Long id, @PathVariable String country) {
        Purchase purchase = purchaseService.findPurchaseById(id);
        if (purchase != null) {
        	BigDecimal purchaseAmountConverted = purchaseService.getConvertedPurchaseAmount(purchase.getPurchaseAmountUSD(), purchase.getPurchaseDate(), country);
            PurchaseDTO dto = PurchaseMapper.INSTANCE.toDTO(purchase);
            dto.setPurchaseAmountConverted(purchaseAmountConverted);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
