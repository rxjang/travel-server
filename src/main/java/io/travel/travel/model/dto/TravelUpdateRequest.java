package io.travel.travel.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TravelUpdateRequest {

    private Long cityId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public TravelUpdateRequest(Long cityId, LocalDateTime startDate, LocalDateTime endDate) {
        this.cityId = cityId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
