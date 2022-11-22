package io.travel.travel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCreateRequest {

    @NotNull(message = "도시를 선택해주세요")
    private Long cityId;

    @NotNull(message = "사용자 아이디가 없습니다.")
    private Long memberId;

    @NotNull(message = "여행 시작일이 없습니다.")
    private LocalDateTime startDate;

    @NotNull(message = "여행 종료일이 없습니다.")
    private LocalDateTime endDate;

}
