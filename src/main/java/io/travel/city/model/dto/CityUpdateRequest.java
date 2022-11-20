package io.travel.city.model.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CityUpdateRequest {

    @NotBlank(message = "도시 이름이 없습니다.")
    private String name;
}
