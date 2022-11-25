package io.travel.city.repository;

import io.travel.city.model.entity.CityViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityViewHistoryRepository extends JpaRepository<CityViewHistory, Long> {
}
