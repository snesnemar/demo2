package com.example.demo2.repository;

import com.example.demo2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByAccount(String account);

    Optional<Member> findByEmail(String email);

    boolean existsByAccount(String account);

    boolean existsByEmail(String email);
}
