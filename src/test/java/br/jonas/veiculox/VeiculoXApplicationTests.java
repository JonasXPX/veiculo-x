package br.jonas.veiculox;

import br.jonas.veiculox.brand.domain.Brand;
import br.jonas.veiculox.brand.domain.repository.BrandRepository;
import br.jonas.veiculox.brand.service.BrandService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class VeiculoXApplicationTests {

    @Autowired
    BrandService service;

    @Mock
    BrandRepository repository;

    @BeforeEach
    void setUp() {
        service.save(Brand.builder()
                .name("bmw")
                .build());
    }

    @Test
    void contextLoads() {
        Brand bmw = service.findOne("bmw");
        Assertions.assertEquals("bmw", bmw.getName());
    }

    @Test
    void contextLoads2() {
        Brand bmw = Assertions.assertDoesNotThrow(() -> service.findOne("bmw"));

        Assertions.assertEquals("bmw", bmw.getName());
        Assertions.assertNotNull(bmw.getId());
    }

    @Test
    void assertRepository() {
        Assertions.assertDoesNotThrow(() -> repository.deleteByName("bmw"));
        Brand bmw = repository.findByName("bmw");

        Assertions.assertNull(bmw);
    }

    @AfterEach
    void tearDown() {
        service.deleteAll();
    }
}
