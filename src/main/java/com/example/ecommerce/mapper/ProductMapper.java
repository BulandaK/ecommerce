package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductAttributeResponse;
import com.example.ecommerce.dto.ProductResponse;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "producer.id", target = "producerId")
    @Mapping(source = "producer.name", target = "producerName")
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    ProductAttributeResponse toAttributeResponse(ProductAttribute attribute);
}
