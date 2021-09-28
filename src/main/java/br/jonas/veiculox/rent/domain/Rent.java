package br.jonas.veiculox.rent.domain;

import br.jonas.veiculox.car.domain.Car;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_car")
    @ManyToOne
    private Car car;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private BigDecimal daily;

}
