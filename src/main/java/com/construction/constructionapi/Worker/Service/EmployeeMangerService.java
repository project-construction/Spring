package com.construction.constructionapi.Worker.Service;

import com.construction.constructionapi.Test.Repository.ScoreRepository;
import com.construction.constructionapi.Worker.DTO.ResponseInfoDTO;
import com.construction.constructionapi.Worker.DTO.ResponseNameDTO;
import com.construction.constructionapi.Worker.DTO.ResponseScoreDTO;
import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Model.Role;
import com.construction.constructionapi.Member.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeMangerService {

    private final MemberRepository memberRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    public EmployeeMangerService(MemberRepository memberRepository,
                                 ScoreRepository scoreRepository){
        this.memberRepository = memberRepository;
        this.scoreRepository = scoreRepository;
    }

    public List<ResponseInfoDTO> allGuestWorkers(){
        List<Member> guestMembers = memberRepository.findAllByRole(Role.GUEST);
        return guestMembers.stream()
                .map(member -> {
                    ResponseInfoDTO guestDTO = ResponseInfoDTO.builder().build();
                    guestDTO.setEmail(member.getEmail());
                    guestDTO.setName(member.getName());
                    guestDTO.setTeam(member.getTeam());
                    guestDTO.setBirth(member.getBirth());
                    guestDTO.setGender(member.getGender());
                    guestDTO.setPhone(member.getPhone());
                    guestDTO.setAddress(member.getAddress());
                    return guestDTO;
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public void approveMember(String email) {
        Member member = memberRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 사용자입니다."));

        member.setApproved(true);
        member.setRole(Role.USER); // 승인 후 권한 변경

        memberRepository.save(member);
    }

    public List<ResponseNameDTO> getAllWorkerNamesInTeam(String teamName){
        List<Member> members = memberRepository.findAllByTeamAndRole(teamName, Role.USER);

        return members.stream()
                .map(member -> {
                    ResponseNameDTO response = new ResponseNameDTO();
                    response.setName(member.getName());
                    response.setEmail(member.getEmail());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public ResponseInfoDTO workerInfo(String email){
        Member mem = memberRepository.findByEmail(email);

        return ResponseInfoDTO.builder()
                .email(mem.getEmail())
                .name(mem.getName())
                .team(mem.getTeam())
                .address(mem.getAddress())
                .birth(mem.getBirth())
                .phone(mem.getPhone())
                .gender(mem.getGender())
                .build();
    }

    public List<ResponseScoreDTO> workerScore(String email){
        String userId = memberRepository.findByEmail(email).getUserid();

        return scoreRepository.findAllByUserId(userId).stream()
                .map(score -> {
                    ResponseScoreDTO dto = ResponseScoreDTO.builder()
                            .doorlock(score.getDoorlock())
                            .hammering(score.getHammering())
                            .nBack(score.getNBack())
                            .simon(score.getSimon())
                            .trafficLight(score.getTrafficLight())
                            .catchMole(score.getCatchMole())
                            .numberPuzzle(score.getNumberPuzzle())
                            .depression(score.getDepression())
                            .anxiety(score.getAnxiety())
                            .stress(score.getStress())
                            .date(score.getDate())
                            .build();

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
