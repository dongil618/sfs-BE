package com.example.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sfs.model.Member;

@Repository
public interface AuthRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
