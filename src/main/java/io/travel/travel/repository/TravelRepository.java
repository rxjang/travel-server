package io.travel.travel.repository;

import io.travel.exception.TravelNotFoundException;
import io.travel.travel.model.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    default Travel getById(Long id) {
        return this.findById(id).orElseThrow(TravelNotFoundException::new);
    }
}
