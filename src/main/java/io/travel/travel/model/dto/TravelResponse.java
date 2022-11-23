package io.travel.travel.model.dto;

import io.travel.travel.model.entity.Travel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TravelResponse {

    private Long id;

    private String cityName;

    private String tourist;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static TravelResponse from(final Travel travel) {
        return TravelResponse.builder()
                .id(travel.getId())
                .cityName(travel.getCity().getName())
                .tourist(travel.getMember().getName())
                .startDate(travel.getStartDate())
                .endDate(travel.getEndDate())
                .build();
    }

    @Builder

    public TravelResponse(Long id, String cityName, String tourist, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.cityName = cityName;
        this.tourist = tourist;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
