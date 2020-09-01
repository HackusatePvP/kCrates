package cc.fatenetwork.kcrates.crates.impl;

import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.inventory.ItemStack;

public class RareCrate extends Keys {

    public RareCrate(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getKeyName() {
        return null;
    }

    @Override
    public ItemStack getCrateItem(Profile profile) {
        return null;
    }

    @Override
    public int getRewardCount() {
        return 0;
    }

    @Override
    public ItemStack[] getRewards() {
        return new ItemStack[0];
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void removeKey(Profile profile) {

    }

    @Override
    public void removeKeys(Profile profile, int amount) {

    }

    @Override
    public void giveKey(Profile profile) {

    }

    @Override
    public void giveKeys(Profile profile, int amount) {

    }

    @Override
    public void setKeys(Profile profile, int amount) {

    }

    @Override
    public int getKeys(Profile profile) {
        return 0;
    }
}
