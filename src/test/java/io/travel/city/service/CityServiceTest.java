package io.travel.city.service;

import io.travel.city.model.dto.CityCreateRequest;
import io.travel.city.model.dto.CityResponse;
import io.travel.city.model.dto.CityUpdateRequest;
import io.travel.city.model.entity.City;
import io.travel.city.repository.CityRepository;
import io.travel.exception.invalidrequest.CannotDeleteCityException;
import io.travel.exception.notfound.CityNotFoundException;
import io.travel.member.model.entity.Member;
import io.travel.member.repository.MemberRepository;
import io.travel.travel.model.dto.TravelCreateRequest;
import io.travel.travel.model.entity.Travel;
import io.travel.travel.repository.TravelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static io.travel.common.Constants.CITY_NAME;
import static io.travel.common.Constants.MEMBER_NAME;
import static io.travel.common.Constants.START_DATE;
import static io.travel.common.Constants.END_DATE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CityServiceTest {

    @Autowired
    CityService cityService;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TravelRepository travelRepository;

    @Test
    void 단일_도시_조회_성공() {
        // given
        City city = CREATE_CITY(CITY_NAME);

        // when
        CityResponse response = cityService.getOne(city.getId());

        // then
        assertAll(() -> {
            assertThat(response.getId()).isEqualTo(city.getId());
            assertThat(response.getName()).isEqualTo(city.getName());
        });
    }

    @Test
    void 존재_하지_않는_단일_도시_조회_예외() {
        // given
        Long id = 0L;

        // when
        // then
        assertThatThrownBy(() -> cityService.getOne(id))
                .isInstanceOf(CityNotFoundException.class);
    }

    @Test
    void 도시_등록_성공() {
        // given
        CityCreateRequest request = new CityCreateRequest(CITY_NAME);

        // when
        Long id = cityService.create(request);
        CityResponse response = cityService.getOne(id);

        // then
        assertAll(() -> {
            assertThat(response.getId()).isEqualTo(id);
            assertThat(response.getName()).isEqualTo(CITY_NAME);
        });
    }

    @Test
    void 도시_수정_성공() {
        // given
        City city = CREATE_CITY(CITY_NAME);

        // when
        CityUpdateRequest request = new CityUpdateRequest("London");
        cityService.update(city.getId(), request);

        // then
        CityResponse response = cityService.getOne(city.getId());
        assertAll(() -> {
            assertThat(response.getId()).isEqualTo(city.getId());
            assertThat(response.getName()).isEqualTo(request.getName());
        });
    }

    @Test
    void 도시_삭제_성공() {
        // given
        City city = CREATE_CITY(CITY_NAME);

        // when
        cityService.delete(city.getId());

        // then
        assertThatThrownBy(() -> cityService.getOne(city.getId()))
                .isInstanceOf(CityNotFoundException.class);
    }

    @Test
    void 여행_일정이_있는_도시_삭제_실패() {
        // given
        City city = CREATE_CITY(CITY_NAME);
        Member member = CREATE_MEMBER(MEMBER_NAME);
        CREATE_TRAVEL(city, member, START_DATE, END_DATE);

        // when
        // then
        assertThatThrownBy(() -> cityService.delete(city.getId()))
                .isInstanceOf(CannotDeleteCityException.class);
    }

    private City CREATE_CITY(final String cityName) {
        CityCreateRequest request = new CityCreateRequest(cityName);
        City city = City.from(request);
        return cityRepository.save(city);
    }

    private Member CREATE_MEMBER(final String memberName) {
        Member member = new Member(memberName);
        return memberRepository.save(member);
    }

    private Travel CREATE_TRAVEL(final City city, final Member member, final LocalDateTime statDate, final LocalDateTime endDate) {
        TravelCreateRequest request = new TravelCreateRequest(city.getId(), member.getId(), statDate, endDate);
        Travel travel = Travel.of(city, member, request);
        return travelRepository.save(travel);
    }

}