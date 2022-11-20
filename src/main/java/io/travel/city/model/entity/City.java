package io.travel.city.model.entity;

import io.travel.city.model.dto.CityCreateRequest;
import io.travel.city.model.dto.CityUpdateRequest;
import io.travel.common.CommonFields;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
@Getter
@NoArgsConstructor
public class City extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public void update(final CityUpdateRequest request) {
        updateName(request.getName());
    }

    private void updateName(final String name) {
        if (StringUtils.hasText(name)) {
            this.name = name;
        }
    }

    public static City from(CityCreateRequest request) {
        return City.builder()
                .name(request.getName())
                .build();
    }

    @Builder
    private City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
