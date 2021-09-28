package br.jonas.veiculox.rent.service;

import br.jonas.veiculox.car.domain.Car;
import br.jonas.veiculox.defaults.GenericException;
import br.jonas.veiculox.rent.domain.Rent;
import br.jonas.veiculox.rent.domain.repository.RentRepository;
import br.jonas.veiculox.rent.dto.RentPeriodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository repository;

    public Rent rentACar(RentPeriodDto rentPeriodDto) {
        verifyRentAvailability(rentPeriodDto);

        Rent rent = createRent(rentPeriodDto);

        return repository.save(rent);
    }

    private Rent createRent(RentPeriodDto rentPeriodDto) {
        Car car = Car.builder()
                .id(rentPeriodDto.getIdCar())
                .build();

        return Rent.builder()
                .car(car)
                .daily(BigDecimal.ONE.multiply(BigDecimal.valueOf(DAYS.between(rentPeriodDto.getStartDateTime(), rentPeriodDto.getEndDateTime()))))
                .startDateTime(rentPeriodDto.getStartDateTime())
                .endDateTime(rentPeriodDto.getEndDateTime())
                .build();
    }

    private void verifyRentAvailability(RentPeriodDto rentPeriodDto) {
        boolean isAvailable = repository.isRentAvailable(rentPeriodDto);
        if (!isAvailable) throwNotAvailable();
    }

    private void throwNotAvailable() {
        throw new GenericException("error.rent.not_available");
    }

}
