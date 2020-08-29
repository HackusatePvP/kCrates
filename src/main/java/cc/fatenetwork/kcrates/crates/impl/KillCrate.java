package cc.fatenetwork.kcrates.crates.impl;

import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
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
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * This is subject to change
     * @return All reward items
     */
    @Override
    public ItemStack[] getRewards() {
        return new ItemStack[] {
                new ItemStack(Material.PAPER, 16)
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
