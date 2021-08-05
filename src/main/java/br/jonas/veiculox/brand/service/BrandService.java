package br.jonas.veiculox.brand.service;

import br.jonas.veiculox.brand.domain.Brand;
import br.jonas.veiculox.brand.domain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public Brand save(Brand brand) {
        return repository.save(brand);
    }

    public Optional<Brand> findById(Long id) {
        return repository.findById(id);
    }

    public Brand findOne(String string) {
        return repository.findByName(string);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
