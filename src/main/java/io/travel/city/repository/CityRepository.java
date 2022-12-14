package io.travel.city.repository;

import io.travel.city.model.entity.City;
import io.travel.exception.notfound.CityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long>, CityCustomRepository {

    default City getById(Long id) {

        return this.findById(id)
                .orElseThrow(CityNotFoundException::new);
    }

}
