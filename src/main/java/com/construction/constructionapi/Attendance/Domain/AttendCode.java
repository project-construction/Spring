package com.construction.constructionapi.Attendance.Domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "AttendCode")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_id")
    private Integer attendId;

    @Column(name = "team")
    private String team;
    @Column(name = "code")
    private Integer code;
    @Column(name = "date")
    private String date;
}
