package org.minerift.zombcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.minerift.zombcore.challenges.ChallengeService;

import java.io.File;

public class ZombCore extends JavaPlugin {

    private static ZombCore instance;
    private static final ChallengeService challengeService = ChallengeService.getInstance();

    public void onEnable() {
        instance = this;

    }

    public static ZombCore getInstance() {
        return instance;
    }

    public static ChallengeService getChallengeService() {
        return challengeService;
    }

    public File getPlayerSaves() {
        File dir = new File(getDataFolder(), "saves");
        if(!dir.exists()) { dir.mkdir(); }

        return dir;
    }
}
