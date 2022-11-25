package io.travel.city.repository;


import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.travel.city.model.dto.CityByMemberResponse;
import io.travel.city.model.dto.QCityByMemberResponse;

import java.time.LocalDateTime;
import java.util.List;

import static io.travel.city.model.entity.QCity.city;
import static io.travel.city.model.entity.QCityViewHistory.cityViewHistory;
import static io.travel.travel.model.entity.QTravel.travel;

public class CityCustomRepositoryImpl implements CityCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CityCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
     * 여행 중인 도시는 중복이 허용되며 노출 개수와 무관
     * 여행 중인 도시: 여행 시작일이 빠른 것 부터
     */
    public List<CityByMemberResponse> findTravelingCity(final Long memberId, final LocalDateTime now) {

        return jpaQueryFactory
                .select(new QCityByMemberResponse(
                        city.id,
                        city.name,
                        travel.startDate,
                        city.createdAt
                ))
                .from(travel)
                .join(travel.city, city)
                .where(travel.member.id.eq(memberId),
                        travel.startDate.before(now),
                        travel.endDate.after(now))
                .orderBy(travel.startDate.asc())
                .fetch();
    }

    /**
     * 중복 없이 상위 10개의 도시만 노출 (Pagination 없음)
     * 여행 예정된 도시: 여행 시작일이 가까운 것부터
     * 하루 이내에 등록된 도시: 기장 최근에 등록한 것부터 ==> 하루 이내에 등록된 도시만 정렬
     * 최근 일주일 이내에 한 번 이상 조회된 도시: 가장 최근에 조회한 것부터
     * 위의 조건에 해당하지 않는 모든 도시: 무작위
     */
    public List<CityByMemberResponse> findCitiesByMemberId(final Long memberId, final LocalDateTime now) {

        DateTimeExpression<LocalDateTime> enrolledOrder = new CaseBuilder()
                .when(city.createdAt.after(now.minusDays(1)))
                .then(city.createdAt)
                .otherwise(LocalDateTime.MIN);

        return jpaQueryFactory
                .select(new QCityByMemberResponse(
                        city.id,
                        city.name,
                        Expressions.asBoolean(false),
                        travel.startDate,
                        city.createdAt,
                        cityViewHistory.createdAt
                ))
                .from(city)
                .leftJoin(travel).on(city.eq(travel.city),
                        travel.member.id.eq(memberId),
                        travel.startDate.after(now))
                .leftJoin(cityViewHistory).on(city.eq(cityViewHistory.city),
                        cityViewHistory.member.id.eq(memberId),
                        cityViewHistory.createdAt.after(now.minusWeeks(1)))
                .orderBy(
                        travel.startDate.asc().nullsLast(),
                        enrolledOrder.desc(),
                        cityViewHistory.createdAt.desc())
                .limit(10)
                .fetch();
    }

}
