package br.jonas.veiculox.car.domain;

import br.jonas.veiculox.rent.domain.Rent;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Rent> rent;

}
