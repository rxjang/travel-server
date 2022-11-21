package io.travel.travel.service;

import io.travel.city.model.entity.City;
import io.travel.city.repository.CityRepository;
import io.travel.member.model.entity.Member;
import io.travel.member.repository.MemberRepository;
import io.travel.travel.model.dto.TravelCreateRequest;
import io.travel.travel.model.dto.TravelResponse;
import io.travel.travel.model.dto.TravelUpdateRequest;
import io.travel.travel.model.entity.Travel;
import io.travel.travel.repository.TravelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TravelService {

    private final CityRepository cityRepository;
    private final MemberRepository memberRepository;
    private final TravelRepository travelRepository;

    public TravelService(final CityRepository cityRepository, final MemberRepository memberRepository, final TravelRepository travelRepository) {
        this.cityRepository = cityRepository;
        this.memberRepository = memberRepository;
        this.travelRepository = travelRepository;
    }

    @Transactional
    public Long create(final TravelCreateRequest request) {
        // TODO date 체크
        City city = cityRepository.getById(request.getCityId());
        Member member = memberRepository.getById(request.getMemberId());
        Travel travel = travelRepository.save(Travel.of(city, member, request));
        return travel.getId();
    }

    public TravelResponse get(final Long id) {
        Travel travel = travelRepository.getById(id);
        return TravelResponse.from(travel);
    }

    @Transactional
    public void update(final Long id, final TravelUpdateRequest request) {
        City city = request.getCityId() == null ? null : cityRepository.getById(id);
        Travel travel = travelRepository.getById(id);
        travel.update(city, request);
        travelRepository.save(travel);
    }

    @Transactional
    public void delete(final Long id) {
        Travel travel = travelRepository.getById(id);
        travelRepository.delete(travel);
    }

}
