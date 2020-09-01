package cc.fatenetwork.kcrates.crates;

import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.entity.Player;


public interface CrateInterface {

    int getCrateKeys(Profile profile, String key);

    void useCrate(Profile profile, Keys key);

    void giveRewards(Keys key, Player player);

    void addKey(String key, Profile profile);

    void setKeys(String key, Profile profile, int amount);
}
