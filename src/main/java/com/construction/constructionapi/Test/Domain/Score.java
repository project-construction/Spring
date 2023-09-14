package com.construction.constructionapi.Test.Domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "test")
@Getter
@Setter
@Builder
@AllArgsConstructor

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

    @Column(name = "NumberPuzzle")
    private Integer numberPuzzle;

    @Column(name = "depression")
    private Integer depression;

    @Column(name = "anxiety")
    private Integer anxiety;

    @Column(name = "stress")
    private Integer stress;

    @Column(name = "date")
    private String date;

    @Column(name = "isCheck")
    private boolean isCheck;

    public Score(){
        this.doorlock = 0;
        this.hammering = (float) 0;
        this.nBack = 0;
        this.simon = 0;
        this.trafficLight = (float) 0;
        this.catchMole = 0;
        this.numberPuzzle = 0;
        this.depression = 0;
        this.anxiety = 0;
        this.stress = 0;
        this.date = String.valueOf(LocalDate.now());
        this.isCheck = false;

    }

    public void setScore(String name, float score){
        switch (name){
            case "catchMole":
                this.catchMole = ((int) score);
                break;
            case "doorLock":
                this.doorlock = ((int) score);
                break;
            case "hammering":
                this.hammering = (score);
                break;
            case "nBack":
                this.nBack = ((int) score);
                break;
            case "numberPuzzle":
                this.numberPuzzle = ((int) score);
                break;
            case "simon":
                this.simon = ((int) score);
                break;
            case "trafficLight":
                this.trafficLight = (score);
                break;
        }

    }
}