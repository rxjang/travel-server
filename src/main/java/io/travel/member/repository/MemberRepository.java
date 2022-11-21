package io.travel.member.repository;

import io.travel.exception.MemberNotFoundException;
import io.travel.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member getById(Long id) {
        return this.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }
}
