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
        if (key.equalsIgnoreCase("vote")) {
            return profile.getVoteKeys();
        }
        return 0;
    }

    @Override
    public void useCrate(Profile profile, Keys key) {
        key.removeKey(profile);
        giveRewards(key, profile.getPlayer());
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
            crate.giveKey(profile);
        }
    }

    @Override
    public void addKey(String key, Profile profile, int amount) {
        Keys crate = Keys.getByName(key);
        if (crate != null) {
            crate.giveKey(profile);
        }
    }

    @Override
    public void setKeys(String key, Profile profile, int amount) {
        Keys crate = Keys.getByName(key);
        if (crate != null) {
            crate.setKeys(profile, amount);
        }
    }

    @Override
    public void removeKey(String key, Profile profile) {
        Keys crate = Keys.getByName(key);
        if (crate != null) {
            crate.removeKey(profile);
        }
    }

    @Override
    public void removeKey(String key, Profile profile, int amount) {
        Keys crate = Keys.getByName(key);
        if (crate != null) {
            crate.removeKey(profile);
        }
    }
}
