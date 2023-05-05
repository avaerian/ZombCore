package org.minerift.zombcore.challenges;

import java.util.Random;

public class ChallengeService {

    public static ChallengeService instance;
    public static ChallengeService getInstance() {
        if(instance == null) { instance = new ChallengeService(); }
        return instance;
    }

    private Challenge[] weeklyChallenges = null;


    private ChallengeService() {
        selectRandomChallenges(2);
    }

    public void selectRandomChallenges(int count) {

        Random random = new Random();

        Challenge[] challenges = new Challenge[count];
        int[] randomTypes = new int[count];

        for(int i = 0; i < count; i++) {
            challenges[i] = null; // TODO

        }
    }

}
