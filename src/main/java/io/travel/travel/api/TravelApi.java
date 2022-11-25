package io.travel.travel.api;

import io.travel.travel.model.dto.TravelCreateRequest;
import io.travel.travel.model.dto.TravelResponse;
import io.travel.travel.model.dto.TravelUpdateRequest;
import io.travel.travel.model.entity.Travel;
import io.travel.travel.service.TravelService;
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
@RequestMapping("/api/v1/travel")
public class TravelApi {

    private final TravelService travelService;

    public TravelApi(final TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping
    public ResponseEntity<Travel> create(@RequestBody @Valid final TravelCreateRequest request) {
        Long id = travelService.create(request);
        return ResponseEntity
                .created(URI.create(String.format("/api/v1/travel/%d", id)))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelResponse> get(@PathVariable final Long id) {
        return ResponseEntity
                .ok(travelService.get(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody final TravelUpdateRequest request) {
        travelService.update(id, request);
        return ResponseEntity
                .noContent()
                .build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        travelService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
