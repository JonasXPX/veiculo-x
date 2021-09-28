package br.jonas.veiculox.rent.domain.repository;

import br.jonas.veiculox.rent.domain.Rent;
import br.jonas.veiculox.rent.dto.RentPeriodDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query("select                              " +
            " count(r.id) = 0 as isav                             " +
            "from Rent r                        " +
            "where                              " +
            " r.car.id = :#{#rentPeriodDto.idCar}    " +
            " and (r.startDateTime between :#{#rentPeriodDto.startDateTime} and :#{#rentPeriodDto.endDateTime}" +
            " or r.endDateTime between :#{#rentPeriodDto.startDateTime} and :#{#rentPeriodDto.endDateTime}" +
            " or :#{#rentPeriodDto.endDateTime} between r.startDateTime and r.endDateTime " +
            " or :#{#rentPeriodDto.startDateTime} between r.startDateTime and r.endDateTime)")
    boolean isRentAvailable(RentPeriodDto rentPeriodDto);

}
