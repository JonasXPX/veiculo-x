package br.jonas.veiculox;

import br.jonas.veiculox.brand.domain.Brand;
import br.jonas.veiculox.brand.domain.repository.BrandRepository;
import br.jonas.veiculox.brand.service.BrandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class SpringTests {

    @MockBean
    BrandRepository repository;

    @Autowired
    BrandService brandService;

    @BeforeEach
    void setUp() {
        Mockito.when(repository.findById(
                Mockito.anyLong()
        )).thenReturn(Optional.of(
                Brand.builder()
                        .id(9999L)
                        .name("TEST")
                        .build()
        ));
    }

    @Test
    void testeAlgumaCoisa() {
        Optional<Brand> service = brandService
                .findById(23123L);

        Assertions.assertTrue(service.isPresent());

        Brand brand = service.get();

        Assertions.assertEquals(9999L, brand.getId());
        Assertions.assertEquals("TEST", brand.getName());
    }

}
