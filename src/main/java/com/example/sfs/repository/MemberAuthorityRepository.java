package com.example.sfs.repository;

import com.example.sfs.model.Member;
import com.example.sfs.model.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority, Long> {

    Set<MemberAuthority> findAllByMemberId(Long memberId);
}
