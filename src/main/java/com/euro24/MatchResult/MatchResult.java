package com.euro24.MatchResult;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchResult {
    private String match;
    private List<UserPrediction> users;
}
