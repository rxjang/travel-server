package io.travel.travel.model.entity;

import io.travel.city.model.entity.City;
import io.travel.common.CommonFields;
import io.travel.exception.invalidrequest.InvalidDateException;
import io.travel.member.model.entity.Member;
import io.travel.travel.model.dto.TravelCreateRequest;
import io.travel.travel.model.dto.TravelUpdateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Travel extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "city_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    public void update(final City city, final TravelUpdateRequest request) {
        updateCity(city);
        updateStartDate(request.getStartDate());
        updateEndDate(request.getEndDate());
    }

    private void updateCity(final City city) {
        if (city != null) {
            this.city = city;
        }
    }

    private void updateStartDate(final LocalDateTime startDate) {
        if (startDate != null) {
            this.startDate = startDate;
        }
    }

    private void updateEndDate(final LocalDateTime endDate) {
        if (endDate != null) {
            this.endDate = endDate;
        }
    }

    public void validateEndDate() {
        if (isEndDateBeforeNow() || isEndDateBeforeStartDate()) {
            throw new InvalidDateException();
        }
    }

    private boolean isEndDateBeforeNow() {
        return this.endDate.isBefore(LocalDateTime.now());
    }

    private boolean isEndDateBeforeStartDate() {
        return this.endDate.isBefore(this.startDate);
    }

    public static Travel of(final City city, final Member member, final TravelCreateRequest request) {
        return Travel.builder()
                .city(city)
                .member(member)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    @Builder
    private Travel(Long id, City city, Member member, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.city = city;
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
