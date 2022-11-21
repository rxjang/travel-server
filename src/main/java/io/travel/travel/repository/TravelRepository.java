package io.travel.travel.repository;

import io.travel.city.model.entity.City;
import io.travel.exception.notfound.TravelNotFoundException;
import io.travel.travel.model.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query("SELECT t FROM Travel t JOIN FETCH t.city JOIN FETCH t.member WHERE t.id = :id")
    Optional<Travel> findById(Long id);

    boolean existsByCity(City city);

    default Travel getById(Long id) {
        return this.findById(id).orElseThrow(TravelNotFoundException::new);
    }
}
