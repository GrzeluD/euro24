package com.euro24.user;

import com.euro24.MatchResult.MatchResult;
import com.euro24.MatchResult.MultipleMatchResult;
import com.euro24.MatchResult.UserPrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void processMatch(MatchResult matchResult) {
        String matchScore = matchResult.getMatch();
        int matchWinner = 0;
        if (matchScore != null) matchWinner = getWinner(matchScore);

        for (UserPrediction userPrediction : matchResult.getUsers()) {
            Optional<User> userOptional = userRepository.findByName(userPrediction.getName());
            User user;

            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                user = new User();
                user.setName(userPrediction.getName());
                userRepository.save(user);
            }

            if (userPrediction.isPredictedTopScorer()) user.setPredictedWinner(true);
            if (userPrediction.isPredictedWinner()) user.setPredictedWinner(true);
            if (matchScore != null) {
                int predictionWinner = getWinner(userPrediction.getPrediction());

                if (matchScore.equals(userPrediction.getPrediction())) {
                    user.addPoints(3);
                    user.incrementExactMatches();
                } else if (matchWinner == predictionWinner) {
                    user.addPoints(1);
                } else {
                    user.addPoints(0);
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
            user.setPreviousPosition(user.getPosition());
            user.setPosition(i + 1);
            userRepository.save(user);
        }
    }

    public void processMatches(MultipleMatchResult multipleMatchResult) {
        for (MatchResult matchResult : multipleMatchResult.getMatches()) {
            processMatch(matchResult);
        }
    }

    private int getWinner(String score) {
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
