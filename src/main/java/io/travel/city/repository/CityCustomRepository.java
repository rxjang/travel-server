package io.travel.city.repository;

import io.travel.city.model.dto.CityByMemberResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface CityCustomRepository {

    List<CityByMemberResponse> findTravelingCity(final Long memberId, final LocalDateTime now);

    List<CityByMemberResponse> findCitiesByMemberId(final Long memberId, final LocalDateTime now);

}
