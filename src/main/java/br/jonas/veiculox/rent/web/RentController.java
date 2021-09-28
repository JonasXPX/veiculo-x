package br.jonas.veiculox.rent.web;

import br.jonas.veiculox.rent.domain.Rent;
import br.jonas.veiculox.rent.dto.RentPeriodDto;
import br.jonas.veiculox.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("rent")
public class RentController {

    private final RentService service;

    @PostMapping
    public ResponseEntity<Rent> rentACar(@RequestBody RentPeriodDto rentPeriodDto) {
        return ResponseEntity.ok(service.rentACar(rentPeriodDto));
    }

}
