package com.example.ecommerce.specyfication;

import com.example.ecommerce.dto.ProductFilter;
import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> byFilter(ProductFilter filter) {
        return hasName(filter.name())
                .and(hasPriceFrom(filter.priceFrom()))
                .and(hasPriceTo(filter.priceTo()))
                .and(hasProducer(filter.producerId()));
    }

    private static Specification<Product> hasName(String name) {
        return (root, query, cb) -> name == null ? null
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Product> hasPriceFrom(BigDecimal priceFrom) {
        return (root, query, cb) -> priceFrom == null ? null
                : cb.greaterThanOrEqualTo(root.get("price"), priceFrom);
    }

    private static Specification<Product> hasPriceTo(BigDecimal priceTo) {
        return (root, query, cb) -> priceTo == null ? null
                : cb.lessThanOrEqualTo(root.get("price"), priceTo);
    }

    private static Specification<Product> hasProducer(Long producerId) {
        return (root, query, cb) -> producerId == null ? null
                : cb.equal(root.get("producer").get("id"), producerId);
    }
}