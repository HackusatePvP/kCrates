package cc.fatenetwork.kcrates.crates;

import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CrateManager implements CrateInterface {

    @Override
    public int getCrateKeys(Profile profile, String key) {
        if (key.equalsIgnoreCase("kill")) {
            return profile.getKillKeys();
        }
        return 0;
    }

    @Override
    public void useCrate(Profile profile, Keys key) {
        if (key == Keys.KILL_CRATE) {
            profile.setKillKeys(profile.getKillKeys() - 1);
            giveRewards(key, profile.getPlayer());
        }
    }

    @Override
    public void giveRewards(Keys key, Player player) {
        Random random = new Random();
        List<ItemStack> items = new ArrayList<>(Arrays.asList(key.getRewards()));
        ItemStack randomItem = items.get(random.nextInt(items.size()));
        player.getInventory().addItem(randomItem);
    }

    @Override
    public void addKey(String key, Profile profile) {
        Keys crate = Keys.getByName(key);
        if (crate != null) {
            if (crate == Keys.KILL_CRATE) {
                profile.setKillKeys(profile.getKillKeys() + 1);
            }
        }
    }

    @Override
    public void setKeys(String key, Profile profile, int amount) {
        Keys crate = Keys.getByName(key);
        if (crate != null) {
            if (crate == Keys.KILL_CRATE) {
                profile.setKillKeys(amount);
            }
        }
    }
}
