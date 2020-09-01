package cc.fatenetwork.kcrates.crates;

import cc.fatenetwork.kcrates.crates.impl.KillCrate;
import cc.fatenetwork.kcrates.crates.impl.VoteCrate;
import cc.fatenetwork.kcrates.profile.Profile;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Keys {
    @Getter private static Map<String, Keys> byName = new HashMap<>();

    public static Keys KILL_CRATE = new KillCrate("Kill");
    public static Keys VOTE_CRATE = new VoteCrate("Vote");

    private String name;

    public Keys(String name) {
        byName.put(name, this);
        this.name = name;
    }

    public static Keys getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract String getKeyName();

    /**
     *
     * @param profile We need this for the lore so we can show them how many total keys they have of that crate
     * @return ItemStack
     */
    public abstract ItemStack getCrateItem(Profile profile);

    public abstract int getRewardCount();

    public abstract ItemStack[] getRewards();

    public abstract boolean isEnabled();

    public abstract void removeKey(Profile profile);

    public abstract void removeKeys(Profile profile, int amount);

    public abstract void giveKey(Profile profile);

    public abstract void giveKeys(Profile profile, int amount);

    public abstract void setKeys(Profile profile, int amount);


}
