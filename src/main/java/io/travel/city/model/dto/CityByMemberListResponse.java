package io.travel.city.model.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CityByMemberListResponse {

    private List<CityByMemberResponse> cities;
}
