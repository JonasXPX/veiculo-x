package br.jonas.veiculox.car.domain.repository;

import br.jonas.veiculox.car.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
