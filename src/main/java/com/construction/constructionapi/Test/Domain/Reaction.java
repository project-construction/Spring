package com.construction.constructionapi.Test.Domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reaction")
public class Reaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reactionId")
    private Long reactionId;
    @Column(name = "userId")
    private String userId;
    @Column(name = "hammeringCorrect")
    private Integer hammeringCorrect;
    @Column(name = "hammeringWrong")
    private Integer hammeringWrong;
    @Column(name = "trafficLightWrong")
    private Integer trafficLightWrong;
    @Column(name = "puzzleCorrect")
    private Integer puzzleCorrect;
    @Column(name = "puzzleWrong")
    private Integer puzzleWrong;
    @Column(name = "date")
    private String date;

    public Reaction(){
        this.hammeringCorrect = 0;
        this.hammeringWrong = 0;
        this.trafficLightWrong = 0;
        this.puzzleCorrect = 0;
        this.puzzleWrong = 0;
    }
    public void setReaction(String name, int correct, int wrong){
        switch (name){
            case "hammering":
                this.hammeringWrong = wrong;
                this.hammeringCorrect = correct;
                break;
            case "numberPuzzle":
                this.puzzleWrong = wrong;
                this.puzzleCorrect = correct;
                break;
            case "trafficLight":
                this.trafficLightWrong = wrong;
                break;
        }
    }

}
