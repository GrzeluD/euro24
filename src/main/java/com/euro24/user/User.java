package com.euro24.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean predictedWinner;
    private boolean predictedTopScorer;
    private Integer points;
    private Integer position;
    private Integer previousPosition;
    private Integer exactMatches;
    private boolean mostCorrectPredictions;

    public User() {
        this.predictedWinner = false;
        this.predictedTopScorer = false;
        this.mostCorrectPredictions = false;
        this.exactMatches = 0;
        this.points = 0;
        this.position = 0;
        this.previousPosition = 0;
    }

    public void addPoints(Integer points) {
        this.points += points;
    }

    public void incrementExactMatches() {
        this.exactMatches++;
    }
}