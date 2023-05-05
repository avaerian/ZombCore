package org.minerift.zombcore.challenges.types;

public abstract class ChallengeType {

    private String description;

    public ChallengeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
