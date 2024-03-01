package com.danna.purchase.mapper;

import com.danna.purchase.dto.PurchaseDTO;
import com.danna.purchase.model.Purchase;

/**
 * @author Cristiane Danna
 */

public class PurchaseMapperImpl implements PurchaseMapper {

	@Override
	public Purchase toEntity(PurchaseDTO dto) {
		Purchase purchase = new Purchase();
		purchase.setDescription(dto.getDescription());
		purchase.setPurchaseDate(dto.getPurchaseDate());
		purchase.setPurchaseAmountUSD(dto.getPurchaseAmountUSD());
		return purchase;
	}

	@Override
	public PurchaseDTO toDTO(Purchase entity) {
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		purchaseDTO.setDescription(entity.getDescription());
		purchaseDTO.setPurchaseDate(entity.getPurchaseDate());
		purchaseDTO.setPurchaseAmountUSD(entity.getPurchaseAmountUSD());
		return purchaseDTO;
	}

}
