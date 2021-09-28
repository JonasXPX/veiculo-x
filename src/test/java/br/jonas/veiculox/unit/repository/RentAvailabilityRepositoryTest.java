package br.jonas.veiculox.unit.repository;

import br.jonas.veiculox.TesteBase;
import br.jonas.veiculox.car.domain.Car;
import br.jonas.veiculox.car.domain.repository.CarRepository;
import br.jonas.veiculox.rent.domain.Rent;
import br.jonas.veiculox.rent.domain.repository.RentRepository;
import br.jonas.veiculox.rent.dto.RentPeriodDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentAvailabilityRepositoryTest extends TesteBase {

    @Autowired
    private RentRepository repository;

    private Car car;

    @Test
    void shouldRunRentAvailabilityQuery() {
        RentPeriodDto build = RentPeriodDto.builder()
                .startDateTime(LocalDateTime.of(2022, 1, 1, 10, 0))
                .endDateTime(LocalDateTime.of(2022, 1, 2, 10, 0))
                .idCar(1L)
                .build();

        assertDoesNotThrow(() -> repository.isRentAvailable(build));
    }

    @Test
    void shouldBeAvailableCarOnSelectedDate() {
        RentPeriodDto rentAvailable = RentPeriodDto.builder()
                .idCar(car.getId())
                .startDateTime(LocalDateTime.of(2022, 1, 3, 10, 0))
                .endDateTime(LocalDateTime.of(2022, 1, 4, 10, 0))
                .build();

        boolean isAvailable = assertDoesNotThrow(() -> repository.isRentAvailable(rentAvailable));
        assertTrue(isAvailable);
    }

    @Test
    void shouldBeNotAvailableCarOnSameSelectedDate() {
        RentPeriodDto rentAvailable = RentPeriodDto.builder()
                .idCar(car.getId())
                .startDateTime(LocalDateTime.of(2022, 1, 1, 9, 0))
                .endDateTime(LocalDateTime.of(2022, 1, 1, 10, 0))
                .build();

        boolean isAvailable = assertDoesNotThrow(() -> repository.isRentAvailable(rentAvailable));
        assertFalse(isAvailable);
    }

    @Test
    void shouldBeNotAvailableCarOnBetweenDates() {
        RentPeriodDto rentAvailable = RentPeriodDto.builder()
                .idCar(car.getId())
                .startDateTime(LocalDateTime.of(2022, 1, 1, 9, 15))
                .endDateTime(LocalDateTime.of(2022, 1, 1, 9, 45))
                .build();

        boolean isAvailable = assertDoesNotThrow(() -> repository.isRentAvailable(rentAvailable));
        assertFalse(isAvailable);
    }

    @Test
    void shouldBeNotAvailableCarOnOverEndDate() {
        RentPeriodDto rentAvailable = RentPeriodDto.builder()
                .idCar(car.getId())
                .startDateTime(LocalDateTime.of(2022, 1, 1, 9, 15))
                .endDateTime(LocalDateTime.of(2022, 1, 1, 11, 0))
                .build();

        boolean isAvailable = assertDoesNotThrow(() -> repository.isRentAvailable(rentAvailable));
        assertFalse(isAvailable);
    }

    @BeforeEach
    public void init(@Autowired CarRepository carRepository) {
        car = Car.builder()
                .name("test_car")
                .build();
        Rent rent = Rent.builder()
                .car(car)
                .startDateTime(LocalDateTime.of(2022, 1, 1 , 9, 0))
                .endDateTime(LocalDateTime.of(2022, 1, 1, 10, 0))
                .daily(BigDecimal.ONE)
                .build();

        car.setRent(List.of(rent));

        carRepository.save(car);
    }

    @AfterEach
    public void destroy(@Autowired CarRepository carRepository) {
        carRepository.deleteAll();
    }



}
