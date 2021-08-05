package br.jonas.veiculox.brand.domain.repository;

import br.jonas.veiculox.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface BrandRepository extends JpaRepository<Brand, Long> {


    Brand findByName(String name);

    @Transactional
    void deleteByName(String name);
}
