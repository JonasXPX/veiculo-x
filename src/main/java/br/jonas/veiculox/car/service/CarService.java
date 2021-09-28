package br.jonas.veiculox.car.service;

import br.jonas.veiculox.car.domain.Car;
import br.jonas.veiculox.car.domain.repository.CarRepository;
import br.jonas.veiculox.defaults.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;

    public Car save(Car car) {
        return repository.save(car);
    }

    public Car update(Car car) {
        if (isNull(car.getId())) {
            throw new RuntimeException("error.illegal_entity_update");
        }
        return repository.save(car);
    }

    public Car findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFound("error.car.not_exists"));
    }

}
