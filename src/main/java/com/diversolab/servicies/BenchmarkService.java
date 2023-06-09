package com.diversolab.servicies;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.diversolab.entities.Benchmark;
import com.diversolab.repositories.BenchmarkRepository;

@AllArgsConstructor
@Service
public class BenchmarkService {

    private final BenchmarkRepository benchmarkRepository;

    public Benchmark findById(String id) {
        return benchmarkRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public Benchmark findByName(String name) {
        return benchmarkRepository.findByName(name).orElseThrow(() -> new NoSuchElementException());
    }

    public List<Benchmark> findAll() {
        return benchmarkRepository.findAll();
    }

    public Benchmark save(Benchmark benchmark) {
        return benchmarkRepository.save(benchmark);
    }

    public boolean existsByName(String name) {
        return benchmarkRepository.existsByName(name);
    }
    
}

    