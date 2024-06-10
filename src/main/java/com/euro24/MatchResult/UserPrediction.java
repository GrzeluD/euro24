package com.euro24.MatchResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrediction {
    private String name;
    private String prediction;
    @Getter
    private boolean predictedWinner;
    @Getter
    private boolean predictedTopScorer;
}
