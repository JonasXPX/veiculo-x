package br.jonas.veiculox.repository;

import br.jonas.veiculox.TesteBase;
import br.jonas.veiculox.brand.domain.Brand;
import br.jonas.veiculox.brand.domain.repository.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestRepository extends TesteBase {

    @Autowired
    BrandRepository repository;

    Brand baseBrand;

    @BeforeEach
    void setUp() {
        repository.save(
                baseBrand = Brand.builder()
                        .name("TEST")
                        .build()
        );
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveOneBrand() {
        final var expectedValue = "BMW";

        Brand brand = Brand.builder()
                .name(expectedValue)
                .build();

        repository.save(brand);

        assertNotNull(brand.getId());
        assertEquals(expectedValue, brand.getName());
    }

    @Test
    void shouldUpdateBrand() {
        final var expectedValue = "TEST_UPDATED";
        baseBrand.setName(expectedValue);
        Brand save = repository.save(baseBrand);

        assertEquals(baseBrand.getId(), save.getId());
        assertEquals(expectedValue, save.getName());
    }

    @Test
    void shouldFindBrandByName() {
        final var expectedValue = "TEST";
        Brand brand = repository.findByName(expectedValue);

        assertEquals(expectedValue, brand.getName());
    }

}
