package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductFilter;
import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.dto.ProductResponse;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.model.Producer;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ProductAttribute;
import com.example.ecommerce.repository.ProducerRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.specyfication.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;
    private final ProductMapper productMapper;

    public Page<ProductResponse> getAll(ProductFilter filter, Pageable pageable) {
        return productRepository.findAll(ProductSpecification.byFilter(filter), pageable)
                .map(productMapper::toResponse);
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        Producer producer = producerRepository.findById(request.producerId())
                .orElseThrow(() -> new EntityNotFoundException("Producer not found: " + request.producerId()));

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setProducer(producer);

        if (request.attributes() != null) {
            request.attributes().forEach(attr -> {
                ProductAttribute attribute = new ProductAttribute();
                attribute.setAttributeKey(attr.attributeKey());
                attribute.setAttributeValue(attr.attributeValue());
                attribute.setProduct(product);
                product.getAttributes().add(attribute);
            });
        }

        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));

        Producer producer = producerRepository.findById(request.producerId())
                .orElseThrow(() -> new EntityNotFoundException("Producer not found: " + request.producerId()));

        product.setName(request.name());
        product.setPrice(request.price());
        product.setProducer(producer);

        product.getAttributes().clear();
        if (request.attributes() != null) {
            request.attributes().forEach(attr -> {
                ProductAttribute attribute = new ProductAttribute();
                attribute.setAttributeKey(attr.attributeKey());
                attribute.setAttributeValue(attr.attributeValue());
                attribute.setProduct(product);
                product.getAttributes().add(attribute);
            });
        }

        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found: " + id);
        }
        productRepository.deleteById(id);
    }
}
