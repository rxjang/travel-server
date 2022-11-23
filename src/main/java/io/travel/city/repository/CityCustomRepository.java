package io.travel.city.repository;

import io.travel.city.model.dto.CityByMemberResponse;

import java.util.List;

public interface CityCustomRepository {

    List<CityByMemberResponse> findCitiesByMemberId(final Long memberId);

}
