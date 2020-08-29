package cc.fatenetwork.kcrates.crates;

import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CrateInterface {

    int getCrateKeys(Profile profile, String key);

    void useCrate(Profile profile, String crate);

    void giveRewards(ItemStack[] itemStacks, Player player);
}
