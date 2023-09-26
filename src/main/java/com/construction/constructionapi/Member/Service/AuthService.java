package com.construction.constructionapi.Member.Service;


import com.construction.constructionapi.Member.DTO.JwtRequestDTO;
import com.construction.constructionapi.Member.DTO.JwtResponseDTO;
import com.construction.constructionapi.Member.DTO.MemberSignupRequestDTO;
import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Model.Role;
import com.construction.constructionapi.Member.Repository.MemberRepository;
import com.construction.constructionapi.Member.Security.JwtTokenProvider;
import com.construction.constructionapi.Member.Security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponseDTO login(JwtRequestDTO request) throws Exception{

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return createJwtToken(authentication);
    }

    private JwtResponseDTO createJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);


        return new JwtResponseDTO(token);
    }

    public boolean signup(MemberSignupRequestDTO request) {
        //중복 유저 점검
        boolean existMember = memberRepository.existsById(request.getUserid());

        if (existMember)
            return false;

        Member member = new Member(request);

        if(request.getRole().equals("MANAGER")){
            member.setRole(Role.MANAGER);
        }else if(request.getRole().equals("USER")){
            member.setRole(Role.GUEST);
        }else{
            return false;
        }

        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);

        return true;
    }

    public Member userDetailInfo(String email){
        return memberRepository.findByEmail(email);
    }

    public Member updateMember(Member updatedMember){

        Member updatedInfo = Member.builder()
                .email(updatedMember.getEmail())
                .userid(updatedMember.getUserid())
                .password(updatedMember.getPassword())
                .name(updatedMember.getName())
                .team(updatedMember.getTeam())
                .birth(updatedMember.getBirth())
                .gender(updatedMember.getGender())
                .phone(updatedMember.getPhone())
                .address(updatedMember.getAddress())
                .build();

        return memberRepository.save(updatedMember);
    }

}