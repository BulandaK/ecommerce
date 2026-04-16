package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProducerResponse;
import com.example.ecommerce.model.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    ProducerResponse toResponse(Producer producer);
}