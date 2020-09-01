package cc.fatenetwork.kcrates.gui;

import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kcrates.Crate;
import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Logger;

public class CrateGui implements Listener {
    private final Crate plugin;

    public CrateGui(Crate plugin) {
        this.plugin = plugin;
    }


    public Inventory getCrateInv(Profile profile) {
        Inventory i = Bukkit.createInventory(null, 27, StringUtil.format("&7* &e&lCrates"));
        //todo setup all crate shops here
        i.setItem(2, getCrate("Kill", profile));
        i.setItem(4, getCrate("Vote", profile));

        //once we set all the crate items we now need to add the borders. We can use a for statement instead of doing i.setItem() for each one

        //make a counter that will stop once we reach the end of the inventory
        for (int counter = 0; counter <= 26; counter++) {
            //this if statement makes sure the inventory isn't full
            if (i.firstEmpty() != -1) {
                //checks to see if the current slot has an item, if not it will be identified as air
                if (i.getItem(counter) == null || i.getItem(counter).getType() == Material.AIR) {
                    //if its air lets add our border item
                    i.setItem(counter, getBorder());
                }
            }
        }
        return i;
    }

    /**
     *
     * @param crate This is the string identifier of the crate, they can viewed in Keys.class
     * @param profile The profile object where all they keys are stored
     * @return ItemStack
     */
    private ItemStack getCrate(String crate, Profile profile) {
        Keys keys = Keys.getByName(crate);
        return keys.getCrateItem(profile);
    }

    /**
     * To view dyes numbers refer to this https://minecraft.gamepedia.com/Dye
     * @return ItemStack
     */
    private ItemStack getBorder() {
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() == null) {
            return;
        }
        if (event.getInventory() == null && event.getClickedInventory() == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
        if (!event.getClickedInventory().getName().equals(getCrateInv(profile).getName())) {
            return;
        }
        event.setCancelled(true);
        if (event.getCurrentItem() == null) {
            return;
        }
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack.getItemMeta().getDisplayName().equals(getCrate("Kill", profile).getItemMeta().getDisplayName())) {
            Keys key = Keys.getByName("Kill");
            if (!key.isEnabled()) {
                player.sendMessage(StringUtil.format("&cThis crate is not available at this time."));
                return;
            }
            /*
            They have just clicked on the kill crate so now if they have a key lets give them their rewards
             */
            int keys = profile.getKillKeys();
            if (keys == 0) {
                player.sendMessage(StringUtil.format("&cYou do not have any keys to use on this crate."));
                return;
            }
            plugin.getCrateInterface().useCrate(profile, key);
            player.openInventory(getCrateInv(profile));
        }
        if (itemStack.getItemMeta().getDisplayName().equals(getCrate("Vote", profile).getItemMeta().getDisplayName())) {
            Keys key = Keys.getByName("Vote");
            if (!key.isEnabled()) {
                player.sendMessage(StringUtil.format("&cThis crate is not available at this time."));
                return;
            }
            int keys = profile.getVoteKeys();
            if (keys == 0) {
                player.sendMessage(StringUtil.format("&cYou do not have any keys to use on this crate."));
                return;
            }
            plugin.getCrateInterface().useCrate(profile, key);
            player.openInventory(getCrateInv(profile));
        }
    }
}
