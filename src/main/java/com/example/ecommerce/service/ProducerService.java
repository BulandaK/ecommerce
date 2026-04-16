package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProducerRequest;
import com.example.ecommerce.dto.ProducerResponse;
import com.example.ecommerce.mapper.ProducerMapper;
import com.example.ecommerce.model.Producer;
import com.example.ecommerce.repository.ProducerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    public Page<ProducerResponse> getAll(Pageable pageable) {
        return producerRepository.findAll(pageable)
                .map(producerMapper::toResponse);
    }

    public ProducerResponse getById(Long id) {
        return producerRepository.findById(id)
                .map(producerMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Producer not found: " + id));
    }

    @Transactional
    public ProducerResponse create(ProducerRequest request) {
        Producer producer = new Producer();
        producer.setName(request.name());
        producer.setDescription(request.description());
        return producerMapper.toResponse(producerRepository.save(producer));
    }

    @Transactional
    public ProducerResponse update(Long id, ProducerRequest request) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producer not found: " + id));

        producer.setName(request.name());
        producer.setDescription(request.description());
        return producerMapper.toResponse(producerRepository.save(producer));
    }

    @Transactional
    public void delete(Long id) {
        if (!producerRepository.existsById(id)) {
            throw new EntityNotFoundException("Producer not found: " + id);
        }
        producerRepository.deleteById(id);
    }
}
