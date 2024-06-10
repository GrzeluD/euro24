package com.euro24.matchResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrediction {
    private String name;
    private String prediction;
    private Integer penaltyWinner;

    @Getter
    private boolean predictedWinner;
    @Getter
    private boolean predictedTopScorer;
    @Getter
    private boolean mostCorrectPredictions;
}
