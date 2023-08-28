package com.construction.constructionapi.Member.Domain;


import com.construction.constructionapi.Member.DTO.MemberSignupRequestDTO;
import com.construction.constructionapi.Member.Model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    private String email;

    private String userid;

    private String password;

    private String name;

    private String team;

    private String birth;

    private String gender;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private boolean approved = false;

    public Member(MemberSignupRequestDTO request) {
        userid = request.getUserid();
        email = request.getEmail();
        password = request.getPassword();
        name = request.getName();
        team = request.getTeam();
        birth = request.getBirth();
        gender = request.getGender();
        phone = request.getPhone();
        address = request.getAddress();
        role = Role.USER; // 회원가입하는 사용자 권한 기본 USER (임시)
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}