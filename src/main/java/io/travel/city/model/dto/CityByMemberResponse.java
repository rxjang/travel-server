package io.travel.city.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class CityByMemberResponse {

    private Long id;

    private String name;

    private boolean isTraveling;

    private LocalDateTime travelStartDate;

    private LocalDateTime enrolledAt;

    private LocalDateTime searchedAt;

    @QueryProjection
    public CityByMemberResponse(Long id, String name, LocalDateTime travelStartDate, LocalDateTime enrolledAt) {
        this.id = id;
        this.name = name;
        this.isTraveling = true;
        this.travelStartDate = travelStartDate;
        this.enrolledAt = enrolledAt;
    }

    @QueryProjection
    public CityByMemberResponse(Long id, String name, boolean isTraveling, LocalDateTime travelStartDate, LocalDateTime enrolledAt, LocalDateTime searchedAt) {
        this.id = id;
        this.name = name;
        this.isTraveling = isTraveling;
        this.travelStartDate = travelStartDate;
        this.enrolledAt = enrolledAt;
        this.searchedAt = searchedAt;
    }
}
