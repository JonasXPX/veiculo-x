package br.jonas.veiculox.integration;

import br.jonas.veiculox.TesteBase;
import br.jonas.veiculox.car.domain.Car;
import br.jonas.veiculox.car.domain.repository.CarRepository;
import br.jonas.veiculox.rent.dto.RentPeriodDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CarIntegrationTest extends TesteBase {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindCarAndRentACar() throws Exception {
         MvcResult mvcResult = createCar();

         Car car = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), Car.class);

         RentPeriodDto rentPeriodDto = RentPeriodDto.builder()
                .idCar(car.getId())
                .startDateTime(LocalDateTime.of(2022, 1, 1, 12, 0))
                .endDateTime(LocalDateTime.of(2022, 1, 2, 12, 0))
                .build();

        mockMvc.perform(
                post("/rent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rentPeriodDto)))
                .andExpect(status().isOk())
                .andReturn();
    }


    private MvcResult createCar() throws Exception {
        Car car = Car.builder()
                .name("test")
                .build();

        return mockMvc.perform(
                post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andReturn();
    }


    @AfterEach
    private void afterAll() {
        carRepository.deleteAll();
    }
}
