package org.minerift.zombcore.challenges;

import org.minerift.zombcore.challenges.types.ChallengeType;

/**
 *  Represents a definition for a challenge
 * @author AvaerianDev
 */
public class Challenge {

    private String name;
    private ChallengeType challengeType;

    public Challenge(String name, ChallengeType challengeType) {
        this.name = name;
        this.challengeType = challengeType;
    }

}
