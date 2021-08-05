package br.jonas.veiculox.brand.web;

import br.jonas.veiculox.brand.domain.Brand;
import br.jonas.veiculox.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService service;

    @PostMapping
    public ResponseEntity<Brand> save(@RequestBody Brand brand) {
        Brand brandCreated = service.save(brand);
        return ResponseEntity
                .created(URI.create(format("brand/%s", brandCreated.getId())))
                .body(brand);
    }

    @PutMapping
    public ResponseEntity<Brand> update(@RequestBody Brand brand) {
        return ResponseEntity.ok(service.save(brand));
    }


}
