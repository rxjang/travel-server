package io.travel.city.service;

import io.travel.city.model.dto.CityByMemberListResponse;
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

import java.time.LocalDateTime;
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

    /**
     * 여행 기간이 지난 여행이라도, 여행이 한 번이라도 등록되면 삭제 불가
     */
    private void checkCouldDeleteCity(City city) {
        if (travelRepository.existsByCity(city)) {
            throw new CannotDeleteCityException();
        }
    }

    /**
     * 중복 없이 상위 10개의 도시만 노출 (Pagination 없음)
     * 여행 중인 도시는 중복이 허용되며 노출 개수와 무관
     * 여행 중인 도시: 여행 시작일이 빠른 것 부터
     * 여행 예정된 도시: 여행 시작일이 가까운 것부터
     * 하루 이내에 등록된 도시: 기장 최근에 등록한 것부터 ==> 하루 이내에 등록된 도시만 정렬
     * 최근 일주일 이내에 한 번 이상 조회된 도시: 가장 최근에 조회한 것부터
     * 위의 조건에 해당하지 않는 모든 도시: 무작위
     */
    public CityByMemberListResponse getCitiesByMember(final Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        List<CityByMemberResponse> list = cityRepository.findTravelingCity(memberId, now);
        list.addAll(cityRepository.findCitiesByMemberId(memberId, now));
        return new CityByMemberListResponse(list);
    }

}
