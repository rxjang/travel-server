package io.travel.city.service;

import io.travel.city.model.dto.CityByMemberResponse;
import io.travel.city.model.dto.CityCreateRequest;
import io.travel.city.model.dto.CityResponse;
import io.travel.city.model.dto.CityUpdateRequest;
import io.travel.city.model.entity.City;
import io.travel.city.repository.CityRepository;
import io.travel.exception.invalidrequest.CannotDeleteCityException;
import io.travel.travel.repository.TravelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CityService {

    private final CityRepository cityRepository;
    private final TravelRepository travelRepository;

    public CityService(final CityRepository cityRepository, final TravelRepository travelRepository) {
        this.cityRepository = cityRepository;
        this.travelRepository = travelRepository;
    }

    @Transactional
    public Long create(final CityCreateRequest request) {
        return cityRepository
                .save(City.from(request))
                .getId();
    }

    public CityResponse getOne(final Long id) {
        final City city = cityRepository.getById(id);
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
        City city = cityRepository.getById(id);
        checkCouldDeleteCity(city);
        cityRepository.delete(city);
    }

    private void checkCouldDeleteCity(City city) {
        if (travelRepository.existsByCity(city)) {
            // TODO check 모든 여행, 진행 중인 여행만?
            throw new CannotDeleteCityException();
        }
    }

    public List<CityByMemberResponse> getCitiesByMember(final Long memberId) {
        return cityRepository.findCitiesByMemberId(memberId);
    }

}
