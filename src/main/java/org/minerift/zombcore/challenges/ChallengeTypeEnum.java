package org.minerift.zombcore.challenges;

import org.minerift.zombcore.challenges.types.ChallengeType;
import org.minerift.zombcore.challenges.types.KillZombiesChallenge;
import org.minerift.zombcore.challenges.types.SurviveTimeChallenge;

public class ChallengeTypeEnum {

    private static ChallengeTypeEnum instance = null;
    public static ChallengeTypeEnum getInstance() {
        if(instance == null) { instance = new ChallengeTypeEnum(); }
        return instance;
    }

    private ChallengeType[][] challenges;

    public static final int KILL_ZOMBIES = 0;
    public static final int SURVIVE_MINUTES = 1;
    public static final int ESCAPE_TIMES = 2;

    private ChallengeTypeEnum() {

        challenges[KILL_ZOMBIES] = new ChallengeType[]{
            new KillZombiesChallenge(20),
            new KillZombiesChallenge(50),
            new KillZombiesChallenge(100),
            new KillZombiesChallenge(200)
        };

        challenges[SURVIVE_MINUTES] = new ChallengeType[]{
            new SurviveTimeChallenge(120),
            new SurviveTimeChallenge(240)
        };

        challenges[ESCAPE_TIMES] = new ChallengeType[]{

        };
    }

    public final ChallengeType[][] getChallenges() {
        return challenges;
    }

}
