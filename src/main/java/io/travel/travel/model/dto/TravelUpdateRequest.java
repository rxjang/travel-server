package io.travel.travel.model.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TravelUpdateRequest {

    private Long cityId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
