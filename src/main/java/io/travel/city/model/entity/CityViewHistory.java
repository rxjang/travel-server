package io.travel.city.model.entity;

import io.travel.common.model.CommonFields;
import io.travel.member.model.entity.Member;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city_view_history")
public class CityViewHistory extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "city_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

}
