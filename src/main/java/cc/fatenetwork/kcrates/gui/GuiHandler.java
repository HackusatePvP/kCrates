package cc.fatenetwork.kcrates.gui;

import cc.fatenetwork.kcrates.Crate;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.Arrays;

@Getter
public class GuiHandler {
    private CrateGui crateGui;
    private final Crate plugin;

    public GuiHandler(Crate plugin) {
        this.plugin = plugin;
        loadGuis();
    }

    void loadGuis() {
        crateGui = new CrateGui(plugin);
        Arrays.asList(crateGui).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }
}
