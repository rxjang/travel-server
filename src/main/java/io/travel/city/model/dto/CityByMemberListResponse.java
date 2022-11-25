package io.travel.city.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CityByMemberListResponse {

    private List<CityByMemberResponse> cities;

    public CityByMemberListResponse(List<CityByMemberResponse> cities) {
        this.cities = cities;
    }
}
