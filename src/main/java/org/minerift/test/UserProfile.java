package org.minerift.test;

import org.bukkit.entity.Player;
import org.minerift.api.database.Configurable;
import org.minerift.api.database.annotation.Saveable;

import java.io.File;
import java.util.Map;

public class UserProfile implements Configurable {

    private Player plr;

    private final String name = plr.getName();

    @Saveable(path = "user.currencies.gold")
    private int gold;

    @Saveable(path = "user.quests")
    private Map<String, String> questData;

    //
    private UserProfile(File file) {

    }

}
