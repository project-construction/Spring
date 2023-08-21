package com.construction.constructionapi.SpringSecurity.Repository;

import com.construction.constructionapi.SpringSecurity.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);
    boolean existsByEmail(String email);
}
