package com.construction.constructionapi.Member.Repository;

import com.construction.constructionapi.Employee.DTO.ResponseGuestDTO;
import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);
    boolean existsByEmail(String email);
    List<Member> findAllByRole(Role role);
    List<Member> findAllByTeamAndRole(String teamName, Role role);
}
