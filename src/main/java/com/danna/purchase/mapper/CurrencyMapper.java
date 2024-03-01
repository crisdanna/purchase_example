package com.danna.purchase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.danna.purchase.dto.CurrencyDTO;
import com.danna.purchase.model.Currency;

/**
 * @author Cristiane Danna
 */

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

	CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    @Mapping(target = "id", ignore = true)
    Currency toEntity(CurrencyDTO dto);

    CurrencyDTO toDTO(Currency entity);
}
