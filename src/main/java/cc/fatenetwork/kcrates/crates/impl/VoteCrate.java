package cc.fatenetwork.kcrates.crates.impl;

import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kcrates.Crate;
import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import cc.fatenetwork.kcrates.server.ServerState;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteCrate extends Keys {

    public VoteCrate(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return "Vote Crate";
    }

    @Override
    public String getKeyName() {
        return "Vote";
    }

    @Override
    public ItemStack getCrateItem(Profile profile) {
        ItemStack itemStack = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &e&lVOTE &7CRATE"));
        List<String> lore = new ArrayList<>();
        lore.add("&7&m-------------------------------");
        lore.add("&7You can get keys by voting for the server.");
        lore.add("");
        lore.add("&7You have &e" + profile.getVoteKeys() + " &7keys.");
        lore.add("");
        lore.add("&7&oClick to open");
        lore.add("&7&m-------------------------------");
        itemMeta.setLore(StringUtil.format(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public int getRewardCount() {
        return 1;
    }

    @Override
    public ItemStack[] getRewards() {
        /*
        ServerState will be used to separate rewards between servers
         */
        if (Crate.getPlugin().getServerManager().getServerState() == ServerState.KITPVP) {
            ItemStack money = new ItemStack(Material.PAPER);
            ItemMeta moneyM = money.getItemMeta();
            moneyM.setDisplayName(StringUtil.format("&7* &a&n&l2K"));
            moneyM.setLore(StringUtil.format(Arrays.asList("&7&m-----------------------------", "&7This voucher is worth &a2k", "", "&7&oClick to redeem",
                    "&7&m-----------------------------")));
            money.setItemMeta(moneyM);
            return new ItemStack[]{
                    money,
                    new ItemStack(Material.GOLDEN_APPLE, 8),
            };
        }
        /*
        Once we add more servers we will need another if statement but now we can just check for kitpvp
         */
        return new ItemStack[]{
                new ItemStack(Material.GOLDEN_APPLE, 8),
        };
    }

    @Override
    public boolean isEnabled() {
        return Crate.getPlugin().getConfiguration("config").getBoolean("vote");
    }

    @Override
    public void removeKey(Profile profile) {
        profile.setVoteKeys(profile.getVoteKeys() - 1);
    }

    @Override
    public void removeKeys(Profile profile, int amount) {
        profile.setVoteKeys(profile.getVoteKeys() - amount);
    }

    @Override
    public void giveKey(Profile profile) {
        profile.setVoteKeys(profile.getVoteKeys() + 1);
    }

    @Override
    public void giveKeys(Profile profile, int amount) {
        profile.setVoteKeys(profile.getVoteKeys() + amount);
    }

    @Override
    public void setKeys(Profile profile, int amount) {
        profile.setVoteKeys(amount);
    }
}
