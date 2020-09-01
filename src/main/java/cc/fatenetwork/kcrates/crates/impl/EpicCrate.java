package cc.fatenetwork.kcrates.crates.impl;

import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kcrates.Crate;
import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import cc.fatenetwork.kcrates.server.ServerState;
import cc.fatenetwork.kvoucher.vouch.VoucherAPI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EpicCrate extends Keys {

    public EpicCrate(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return "Epic Crate";
    }

    @Override
    public String getKeyName() {
        return "Epic";
    }

    @Override
    public ItemStack getCrateItem(Profile profile) {
        ItemStack itemStack = new ItemStack(Material.ENDER_CHEST);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &5&lEPIC &7CRATE"));
        List<String> lore = new ArrayList<>();
        lore.add("&7&m-------------------------------");
        lore.add("&7You can purchase this key &7(&c/buy&7).");
        lore.add("");
        lore.add("&7You have &b" + profile.getKillKeys() + " &7keys.");
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
        if (Crate.getPlugin().getServerManager().getServerState() == ServerState.KITPVP) {
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
            return new ItemStack[] {
                    VoucherAPI.getVoucherItem("20K"),
                    VoucherAPI.getVoucherItem("Master Rank Temp"),
                    VoucherAPI.getVoucherItem("Saber Rank Temp"),
                    sword,
                    new ItemStack(Material.GOLDEN_APPLE, 64)
            };
        }
        return new ItemStack[0];
    }

    @Override
    public boolean isEnabled() {
        return Crate.getPlugin().getConfiguration("config").getBoolean("epic");
    }

    @Override
    public void removeKey(Profile profile) {
        profile.setKillKeys(profile.getKillKeys() - 1);
    }

    @Override
    public void removeKeys(Profile profile, int amount) {
        profile.setKillKeys(profile.getKillKeys() - amount);
    }

    @Override
    public void giveKey(Profile profile) {
        profile.setKillKeys(profile.getKillKeys() + 1);
    }

    @Override
    public void giveKeys(Profile profile, int amount) {
        profile.setKillKeys(profile.getKillKeys() + amount);
    }

    @Override
    public void setKeys(Profile profile, int amount) {
        profile.setKillKeys(amount);
    }

    @Override
    public int getKeys(Profile profile) {
        return profile.getVoteKeys();
    }
}
