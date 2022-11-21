package io.travel.travel.model.dto;

import io.travel.travel.model.entity.Travel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TravelResponse {

    private Long id;

    private String cityName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static TravelResponse from(final Travel travel) {
        return TravelResponse.builder()
                .id(travel.getId())
                .cityName(travel.getCity().getName())
                .startDate(travel.getStartDate())
                .endDate(travel.getEndDate())
                .build();
    }

    @Builder
    public TravelResponse(Long id, String cityName, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.cityName = cityName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
