package com.danna.purchase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.danna.purchase.dto.PurchaseDTO;
import com.danna.purchase.model.Purchase;

/**
 * @author Cristiane Danna
 */

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

	PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(target = "id", ignore = true)
    Purchase toEntity(PurchaseDTO dto);

    PurchaseDTO toDTO(Purchase entity);
}
