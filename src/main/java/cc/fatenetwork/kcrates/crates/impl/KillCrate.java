package cc.fatenetwork.kcrates.crates.impl;

import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KillCrate extends Keys {

    /**
     * This is a exmaple crate, we will figure out reward randoms down the road when we there
     * @param name identifier
     */

    /**
     * This is the key constructor
     * @param name Default identifier
     */
    public KillCrate(String name) {
        super(name);
    }

    /**
     *
     * @return This will return the identifier for when we need to do an inventory click event
     */
    @Override
    public String getName() {
        return "Kill Crate";
    }

    @Override
    public String getKeyName() {
        return "Kill";
    }

    /**
     *
     * @return The item that will be in the gui
     */
    @Override
    public ItemStack getCrateItem(Profile profile) {
        ItemStack itemStack = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &b&lKILL &7CRATE"));
        List<String> lore = new ArrayList<>();
        lore.add("&7&m-------------------------------");
        lore.add("&7You can get keys by killing players.");
        lore.add("");
        lore.add("&7You have &b" + profile.getKillKeys() + " &7keys.");
        lore.add("");
        lore.add("&7&oClick to open");
        lore.add("&7&m-------------------------------");
        itemMeta.setLore(StringUtil.format(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     *
     * @return The amount of rewards they can win from the crate
     */
    @Override
    public int getRewardCount() {
        return 1;
    }

    /**
     * This is subject to change
     * @return All reward items
     */
    @Override
    public ItemStack[] getRewards() {
        ItemStack money = new ItemStack(Material.PAPER);
        ItemMeta moneyM = money.getItemMeta();
        moneyM.setDisplayName(StringUtil.format("&7* &a&n&l2K"));
        moneyM.setLore(StringUtil.format(Arrays.asList("&7&m-----------------------------", "&7This voucher is worth &a2k", "", "&7&oClick to redeem",
                "&7&m-----------------------------")));
        money.setItemMeta(moneyM);
        return new ItemStack[] {
                money,
                new ItemStack(Material.DIAMOND_SWORD),
                new ItemStack(Material.DIAMOND_HELMET),
                new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_LEGGINGS),
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.GOLDEN_APPLE),
                new ItemStack(Material.BOOK, 16)
        };
    }

    /**
     *
     * @return if the crate is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
