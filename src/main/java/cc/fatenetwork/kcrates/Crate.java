package cc.fatenetwork.kcrates;

import cc.fatenetwork.kcrates.database.MongoManager;
import cc.fatenetwork.kcrates.profile.ProfileManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Crate extends JavaPlugin {
    private ProfileManager profileManager;
    private MongoManager mongoManager;
    @Getter private static Crate plugin;


    @Override
    public void onEnable() {
        plugin = this;

        registerManagers();
    }

    @Override
    public void onDisable() {

        plugin = null;
    }

    void registerManagers() {
        mongoManager = new MongoManager(this);
        profileManager = new ProfileManager();
    }
}
