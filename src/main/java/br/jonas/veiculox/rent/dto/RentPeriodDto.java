package br.jonas.veiculox.rent.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentPeriodDto {

    private Long idCar;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

}
