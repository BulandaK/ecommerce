package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProducerRequest;
import com.example.ecommerce.dto.ProducerResponse;
import com.example.ecommerce.service.ProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public Page<ProducerResponse> getAll(
            @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return producerService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ProducerResponse getById(@PathVariable Long id) {
        return producerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProducerResponse create(@RequestBody @Valid ProducerRequest request) {
        return producerService.create(request);
    }

    @PutMapping("/{id}")
    public ProducerResponse update(@PathVariable Long id, @RequestBody @Valid ProducerRequest request) {
        return producerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        producerService.delete(id);
    }
}