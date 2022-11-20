package io.travel.city.model.dto;

import io.travel.city.model.entity.City;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityResponse {

    private Long id;

    private String name;

    public static CityResponse from(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

    @Builder
    public CityResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
