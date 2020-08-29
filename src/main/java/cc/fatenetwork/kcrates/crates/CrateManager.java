package cc.fatenetwork.kcrates.crates;

import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CrateManager implements CrateInterface {

    @Override
    public int getCrateKeys(Profile profile, String key) {
        if (key.equalsIgnoreCase("kill")) {
            return profile.getKillKeys();
        }
        return 0;
    }

    @Override
    public void useCrate(Profile profile, String crate) {
        if (crate.equalsIgnoreCase("kill")) {
            profile.setKillKeys(profile.getKillKeys() - 1);
        }
    }

    @Override
    public void giveRewards(ItemStack[] itemStacks, Player player) {
        //todo give the player there select rewards which are picked by a random and send them a message of what they won

    }
}
