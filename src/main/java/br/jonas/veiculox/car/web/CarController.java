package br.jonas.veiculox.car.web;

import br.jonas.veiculox.car.domain.Car;
import br.jonas.veiculox.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static java.net.URI.create;

@RequiredArgsConstructor
@RestController
@RequestMapping("car")
public class CarController {

    private final CarService service;

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car) {
        Car entity = service.save(car);
        return ResponseEntity
                .created(create(format("url/%s", entity.getId())))
                .body(entity);
    }

    @PutMapping
    public ResponseEntity<Car> update(@RequestBody Car car) {
        return ResponseEntity.ok(service.save(car));
    }


}
