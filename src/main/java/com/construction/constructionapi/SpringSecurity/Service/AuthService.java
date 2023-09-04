package com.construction.constructionapi.SpringSecurity.Service;


import com.construction.constructionapi.SpringSecurity.DTO.JwtRequestDTO;
import com.construction.constructionapi.SpringSecurity.DTO.JwtResponseDTO;
import com.construction.constructionapi.SpringSecurity.DTO.MemberSignupRequestDTO;
import com.construction.constructionapi.SpringSecurity.Domain.Member;
import com.construction.constructionapi.SpringSecurity.Repository.MemberRepository;
import com.construction.constructionapi.SpringSecurity.Security.JwtTokenProvider;
import com.construction.constructionapi.SpringSecurity.Security.UserDetailsImpl;
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

        System.out.println(request.getEmail() + " " + request.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        System.out.println("test");
        return createJwtToken(authentication);
    }

    private JwtResponseDTO createJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);

        System.out.println(token);

        return new JwtResponseDTO(token);
    }

    public String signup(MemberSignupRequestDTO request) {
        //중복 유저 점검
        boolean existMember = memberRepository.existsById(request.getUserid());

        if (existMember)
            return null;

        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);


        memberRepository.save(member);

        return member.getEmail();
    }
}