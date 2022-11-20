package io.travel.city.service;

import io.travel.city.model.dto.CityCreateRequest;
import io.travel.city.model.dto.CityResponse;
import io.travel.city.model.dto.CityUpdateRequest;
import io.travel.city.model.entity.City;
import io.travel.city.repository.CityRepository;
import io.travel.exception.CityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    @Transactional
    public Long save(final CityCreateRequest request) {
        return cityRepository
                .save(City.from(request))
                .getId();
    }

    public CityResponse getOne(final Long id) {
        final City city = get(id);
        return CityResponse.from(city);
    }


    @Transactional
    public void update(final Long id, final CityUpdateRequest request) {
        City city = get(id);
        city.update(request);
        cityRepository.save(city);
    }

    @Transactional
    public void delete(final Long id) {
        // TODO 지정된 여행 있는지 체크
        City city = get(id);
        cityRepository.delete(city);
    }

    private City get(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(CityNotFoundException::new);
    }
}
