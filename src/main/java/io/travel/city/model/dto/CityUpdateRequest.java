package io.travel.city.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CityUpdateRequest {

    @NotBlank(message = "도시 이름이 없습니다.")
    private String name;
}
