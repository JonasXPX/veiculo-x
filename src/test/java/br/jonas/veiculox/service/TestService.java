package br.jonas.veiculox.service;

import br.jonas.veiculox.TesteBase;
import br.jonas.veiculox.brand.domain.Brand;
import br.jonas.veiculox.brand.domain.repository.BrandRepository;
import br.jonas.veiculox.brand.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TestService extends TesteBase {

    @MockBean
    BrandRepository mockedRepository;

    @Autowired
    BrandService service;

    @BeforeEach
    void setUp() {
        when(mockedRepository.save(any(Brand.class)))
                .thenReturn(Brand.builder().id(9999L).name("bmw").build());
    }

    /**
     * Verifica se a entidade foi salva pela serviço;
     * Camada de repositório é simulada, nada é salvo no banco;
     * Feito uma verificação se o save do repositório é chamada;
     */
    @Test
    void shouldSaveBrandAndVerifyIfCalledSaveMethodOnRepository() {
        Brand brand = Brand.builder()
                .name("bmw")
                .build();
        Brand savedBrand = service.save(brand);

        verify(mockedRepository, times(1))
                .save(any(Brand.class));

        assertNotNull(savedBrand);
        assertNotNull(savedBrand.getId());
    }
}
