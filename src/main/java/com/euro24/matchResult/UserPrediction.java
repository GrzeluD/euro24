package com.euro24.matchResult;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPrediction {
    private String name;
    private List<String> predictions;
    private List<String> penaltyPredictions;

    @Getter
    private boolean predictedWinner;
    @Getter
    private boolean predictedTopScorer;
    @Getter
    private boolean mostCorrectPredictions;
}
