package cc.fatenetwork.kcrates.gui;

import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CrateGui implements Listener {

    public Inventory getCrateInv(Profile profile) {
        Inventory i = Bukkit.createInventory(null, 36, StringUtil.format("&7* &e&lCrates"));
        //todo setup all crate shops here
        i.setItem(2, getCrate("Kill Crate", profile));

        //once we set all the crate items we now need to add the borders. We can use a for statement instead of doing i.setItem() for each one

        //make a counter that will stop once we reach the end of the inventory
        for (int counter = 0; counter <= i.getSize(); counter++) {
            //this if statement makes sure the inventory isn't full
            if (i.firstEmpty() != -1) {
                //checks to see if the current slot has an item, if not it will be identified as air
                if (i.getItem(counter).getType() == Material.AIR) {
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
        if (Keys.getByName(crate) != null) {
            Keys keys = Keys.getByName(crate);
            return keys.getCrateItem(profile);
        }
        return null;
    }

    /**
     * To view dyes numbers refer to this https://minecraft.gamepedia.com/Dye
     * @return ItemStack
     */
    private ItemStack getBorder() {
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

    }
}
