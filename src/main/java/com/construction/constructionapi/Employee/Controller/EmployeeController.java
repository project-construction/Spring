package com.construction.constructionapi.Employee.Controller;

import com.construction.constructionapi.Employee.DTO.ResponseInfoDTO;
import com.construction.constructionapi.Employee.DTO.ResponseScoreDTO;
import com.construction.constructionapi.Employee.Service.EmployeeMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "https://web-template-3prof2llkxuyz4l.sel4.cloudtype.app")
public class EmployeeController {
    //근로자 관리

    private final EmployeeMangerService employeeMangerService;

    @Autowired
    public EmployeeController(EmployeeMangerService employeeMangerService){
        this.employeeMangerService = employeeMangerService;
    }

    @GetMapping("/wait")
    public List<ResponseInfoDTO> awaitingApprovals(){
        return employeeMangerService.allGuestWorkers();
    }

    @PostMapping("/approval")
    public String approvalGuest(@RequestParam String email){
        employeeMangerService.approveMember(email);
        return "success";
    }

    @PostMapping("/team")
    public List<String> getAllWorkerNamesInTeam(@RequestParam String teamName) {
        return employeeMangerService.getAllWorkerNamesInTeam(teamName);
    }

    //기본 정보
    @PostMapping("/info")
    public ResponseInfoDTO detailInfo(@RequestParam String email) {
        return employeeMangerService.workerInfo(email);
    }

    //검사 정보
    @PostMapping("/score")
    public List<ResponseScoreDTO> detailScore(@RequestParam String email) {
        return employeeMangerService.workerScore(email);
    }


}