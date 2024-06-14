package com.euro24.user;

import com.euro24.matchResult.MatchResult;
import com.euro24.matchResult.MultipleMatchResult;
import com.euro24.matchResult.UserPrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User prediction) {
        return userRepository.save(prediction);
    }

    public void processMatchAndUsers(List<MatchResult> matchResults, List<UserPrediction> userPredictions) {
        for (UserPrediction userPrediction : userPredictions) {
            if (userPrediction.getName() == null || userPrediction.getName().isEmpty()) {
                continue;
            }

            Optional<User> userOptional = userRepository.findByName(userPrediction.getName());
            User user;

            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                user = new User();
                user.setName(userPrediction.getName());
            }

            if (userPrediction.isPredictedTopScorer()) {
                user.setPredictedTopScorer(true);
                user.addPoints(5);
            }
            if (userPrediction.isPredictedWinner()) {
                user.setPredictedWinner(true);
                user.addPoints(5);
            }
            if (userPrediction.isMostCorrectPredictions()) {
                user.setMostCorrectPredictions(true);
                user.addPoints(5);
            }

            for (int i = 0; i < matchResults.size(); i++) {
                MatchResult matchResult = matchResults.get(i);
                String userPredictionScore = userPrediction.getPredictions().get(i);
                Integer matchWinner = getWinner(matchResult.getMatch());

                if (userPredictionScore != null && !userPredictionScore.isEmpty()) {
                    int predictionWinner = getWinner(userPredictionScore);

                    if (matchResult.getMatch().equals(userPredictionScore)) {
                        user.addPoints(3);
                        user.incrementExactMatches();
                    } else if (matchWinner == predictionWinner) {
                        user.addPoints(1);
                    } else {
                        user.addPoints(0);
                    }

                    if (userPrediction.getPenaltyPredictions() != null && userPrediction.getPenaltyPredictions().size() > i) {
                        String penaltyPrediction = userPrediction.getPenaltyPredictions().get(i);
                        if (penaltyPrediction != null && !penaltyPrediction.isEmpty() &&
                                penaltyPrediction.equals(String.valueOf(matchResult.getActualPenaltyWinner()))) {
                            user.addPoints(1);
                        }
                    }
                }
            }

            userRepository.save(user);
        }

        updateUserPositions();
    }

    private void updateUserPositions() {
        List<User> users = userRepository.findAll();

        users.sort((u1, u2) -> {
            int pointComparison = u2.getPoints().compareTo(u1.getPoints());
            return pointComparison != 0 ? pointComparison : u2.getExactMatches().compareTo(u1.getExactMatches());
        });

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            user.setPosition(i + 1);
            userRepository.save(user);
        }
    }

    public void processMatches(MultipleMatchResult multipleMatchResult) {
        List<MatchResult> matchResults = multipleMatchResult.getMatches();
        List<UserPrediction> userPredictions = multipleMatchResult.getUsers();

        for (User user : userRepository.findAll()) {
            user.setPreviousPosition(user.getPosition());
            userRepository.save(user);
        }

        processMatchAndUsers(matchResults, userPredictions);
    }

    private int getWinner(String score) {
        if(Objects.equals(score, "")) return 0;
        String[] parts = score.split(":");
        int team1Score = Integer.parseInt(parts[0]);
        int team2Score = Integer.parseInt(parts[1]);

        if (team1Score > team2Score) {
            return 1;
        } else if (team2Score > team1Score) {
            return 2;
        } else {
            return 0;
        }
    }
}
