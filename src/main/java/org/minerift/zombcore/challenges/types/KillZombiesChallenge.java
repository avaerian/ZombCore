package org.minerift.zombcore.challenges.types;

public class KillZombiesChallenge extends ChallengeType {

    private int kills;

    public KillZombiesChallenge(int kills) {
        super("Kill " + kills + " zombies.");
        this.kills = kills;
    }

    public int getKills() {
        return kills;
    }
}
