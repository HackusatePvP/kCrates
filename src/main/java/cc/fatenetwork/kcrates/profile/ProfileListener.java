package cc.fatenetwork.kcrates.profile;

import cc.fatenetwork.kcrates.Crate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {
    private final Crate plugin;

    public ProfileListener(Crate plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());
        plugin.getProfileManager().getProfiles().put(player.getUniqueId(), profile);
        if (plugin.getMongoManager().getUUID(player.getUniqueId()) == null) {
            plugin.getMongoManager().insertPlayer(player.getUniqueId());
        } else {
            plugin.getMongoManager().getPlayer(player.getUniqueId());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getMongoManager().update(player.getUniqueId());
    }
}
