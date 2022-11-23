package io.travel.city.api;

import io.travel.city.model.dto.CityCreateRequest;
import io.travel.city.model.dto.CityResponse;
import io.travel.city.model.dto.CityUpdateRequest;
import io.travel.city.model.entity.City;
import io.travel.city.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/cities")
public class CityApi {

    private final CityService cityService;

    public CityApi(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<City> create(@RequestBody @Valid final CityCreateRequest request) {
        Long id = cityService.create(request);
        return ResponseEntity
                .created(URI.create(String.format("/api/v1/city/%d", id)))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> get(@PathVariable final Long id) {
        return ResponseEntity
                .ok(cityService.getOne(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id,
                                       @RequestBody @Valid final CityUpdateRequest request) {
        cityService.update(id, request);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        cityService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
