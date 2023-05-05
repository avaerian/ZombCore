package org.minerift.zombcore;

import org.bukkit.entity.Player;
import org.minerift.api.database.annotation.Saveable;
import org.minerift.api.database.YamlConfigurable;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ZombProfile {

    private static Map<Player, ZombProfile> playerProfileMap = new HashMap<>();

    private static final Yaml yaml = new Yaml();
    private static final ZombCore zombCore = ZombCore.getInstance();

    public static ZombProfile load(Player plr) {

        File file = new File(zombCore.getPlayerSaves(), plr.getUniqueId().toString());
        YamlConfigurable config = YamlConfigurable.load(file);

        ZombProfile profile = new ZombProfile(plr, config);
        playerProfileMap.put(plr, profile);
        return profile;
    }

    private Player plr;

    @Saveable(path = "gold")
    private int gold;

    private ZombProfile(Player plr, YamlConfigurable config) {
        this.plr = plr;
        this.gold = config.read("user.currencies.gold");
    }

    public void save() {

        // Get file from saves directory
        File file = new File(zombCore.getPlayerSaves(), plr.getUniqueId().toString());
        YamlConfigurable config = YamlConfigurable.load(file);

        config.write("user.currencies.gold", gold);
        config.push(true);

    }

    public void unload() {
        playerProfileMap.remove(this);
    }

}
