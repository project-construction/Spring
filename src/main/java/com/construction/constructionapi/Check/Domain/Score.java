package com.construction.constructionapi.Check.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "Doorlock")
    private Integer doorlock;

    @Column(name = "Hammering")
    private Float hammering;

    @Column(name = "Nback")
    private Integer nBack;

    @Column(name = "Simon")
    private Integer simon;

    @Column(name = "TrafficLight")
    private Float trafficLight;

    @Column(name = "CatchMole")
    private Integer catchMole;

    @Column(name = "depression")
    private Integer depression;

    @Column(name = "anxiety")
    private Integer anxiety;

    @Column(name = "stress")
    private Integer stress;

    @Column(name = "date")
    private Date date;
}