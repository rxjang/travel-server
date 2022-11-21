package io.travel.city.service;

import io.travel.city.model.dto.CityCreateRequest;
import io.travel.city.model.dto.CityResponse;
import io.travel.city.model.dto.CityUpdateRequest;
import io.travel.city.model.entity.City;
import io.travel.city.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional
    public Long save(final CityCreateRequest request) {
        return cityRepository
                .save(City.from(request))
                .getId();
    }

    public CityResponse getOne(final Long id) {
        final City city =cityRepository.getById(id);
        return CityResponse.from(city);
    }


    @Transactional
    public void update(final Long id, final CityUpdateRequest request) {
        City city = cityRepository.getById(id);
        city.update(request);
        cityRepository.save(city);
    }

    @Transactional
    public void delete(final Long id) {
        // TODO 지정된 여행 있는지 체크
        City city = cityRepository.getById(id);
        cityRepository.delete(city);
    }

}
