package com.example.product_service.service;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public ProductDto create(ProductDto dto) {
        Product p = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .stock(dto.getStock() == null ? 0 : dto.getStock())
                .build();
        Product saved = repo.save(p);
        return toDto(saved);
    }

    public ProductDto update(Long id, ProductDto dto) {
        Product p = repo.findById(id).orElseThrow();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setImageUrl(dto.getImageUrl());
        p.setStock(dto.getStock());
        Product updated = repo.save(p);
        return toDto(updated);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public ProductDto getById(Long id) {
        return toDto(repo.findById(id).orElseThrow());
    }

    public List<ProductDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private ProductDto toDto(Product p) {
        return ProductDto.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .imageUrl(p.getImageUrl())
                .stock(p.getStock())
                .build();
    }
}
