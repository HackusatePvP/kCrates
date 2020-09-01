package cc.fatenetwork.kcrates;

import cc.fatenetwork.kcrates.commands.CrateCommand;
import cc.fatenetwork.kcrates.commands.KeyCommand;
import cc.fatenetwork.kcrates.configurations.ConfigFile;
import cc.fatenetwork.kcrates.crates.CrateInterface;
import cc.fatenetwork.kcrates.crates.CrateManager;
import cc.fatenetwork.kcrates.database.MongoManager;
import cc.fatenetwork.kcrates.gui.GuiHandler;
import cc.fatenetwork.kcrates.profile.ProfileListener;
import cc.fatenetwork.kcrates.profile.ProfileManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Getter
public final class Crate extends JavaPlugin {
    private ProfileManager profileManager;
    private MongoManager mongoManager;
    private GuiHandler guiHandler;
    private CrateInterface crateInterface;
    private List<ConfigFile> files = new ArrayList<>();
    private final Logger log = Bukkit.getLogger();
    @Getter private static Crate plugin;


    @Override
    public void onEnable() {
        plugin = this;
        log.info("[kCrate] loading plugin...");
        loadConfiguration();
        registerManagers();
        registerEvents();
        log.info("[kCrate] loading commands");
        registerCommands();
        log.info("[kCrate] done!");
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            getMongoManager().update(player.getUniqueId());
        });
        plugin = null;
    }

    public ConfigFile getConfiguration(String name) {
        return files.stream().filter(config -> config.getName().equals(name)).findFirst().orElse(null);
    }

    void loadConfiguration() {
        files.addAll(Arrays.asList(
                new ConfigFile("config")
        ));
    }

    void registerManagers() {
        mongoManager = new MongoManager(this);
        profileManager = new ProfileManager();
        guiHandler = new GuiHandler(this);
        crateInterface = new CrateManager();
    }

    void registerCommands() {
        getCommand("crate").setExecutor(new CrateCommand(this));
        getCommand("key").setExecutor(new KeyCommand(this));
    }

    void registerEvents() {
        Arrays.asList(new ProfileListener(this)).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }
}
