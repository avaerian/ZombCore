package org.minerift.zombcore.challenges.types;

public class SurviveTimeChallenge extends ChallengeType {

    private int seconds;

    public SurviveTimeChallenge(int seconds) {
        super("Survive for " + seconds + " seconds.");
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
