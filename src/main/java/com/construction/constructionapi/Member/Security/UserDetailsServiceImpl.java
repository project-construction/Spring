package com.construction.constructionapi.Member.Security;


import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Model.Role;
import com.construction.constructionapi.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
                .orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 사용자 입니다"));

        boolean isManager = member.getRole() == Role.MANAGER;
        boolean isApproved = member.isApproved(); // 승인 여부

        return new UserDetailsImpl(member, isManager, isApproved);
    }
}