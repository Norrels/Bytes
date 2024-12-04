package com.bytes.bytes.contexts.kitchen.utils;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.ProductRequest;
import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities.ProductEntity;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.shared.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO product);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imgUrl", ignore = true)
    @Mapping(target = "createdById", ignore = true)
    ProductDTO toProductDTO(ProductRequest productRequest);

    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);
}
