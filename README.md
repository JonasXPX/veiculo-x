### Exemplo de Testes


#### Teste unitário | Repositório 

```java

class TestService extends TesteBase {

    @Autowired
    BrandRepository repository;
    
    @Test // Define que o método a baixo é um teste
    @Disabled // permite desativar o teste. É possível escrever um motivo
    void shouldSaveOneBrand() {
        final var expectedValue = "BMW";

        Brand brand = Brand.builder()
                .name(expectedValue)
                .build();

        repository.save(brand);

        assertNotNull(brand.getId()); // acerta que o valor não é nulo
        assertEquals(expectedValue, brand.getName()); // acerta que o valor esperado é o valor recebido
    }

}
```

#### Teste unitário | Serviço 

Repositório com Mock

```java

class TestService extends TesteBase {
    @BeforeEach
    void setUp() {
        when(mockedRepository.save(any(Brand.class)))
                .thenReturn(Brand.builder().id(9999L).name("bmw").build()); // Indepedente do Objeto Brand,
                                                                            // o teste sempre vai retornar o valor informado no mock
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
        Brand savedBrand = service.save(brand); // retorna o valor falso informado no mock

        verify(mockedRepository, times(1))
                .save(any(Brand.class)); // verifica se o método foi chamado apenas 1 vez apenas

        assertNotNull(savedBrand);
        assertNotNull(savedBrand.getId());
    }
}
```

