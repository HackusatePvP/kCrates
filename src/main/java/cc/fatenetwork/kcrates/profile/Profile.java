package cc.fatenetwork.kcrates.profile;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class Profile {
    private UUID uuid;
    private int killKeys;
    private int voteKeys;

    /**
     * This will create a profile in which we can store keys across the network
     * @param uuid
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * This is a lot easier then saving a player object, its also better for performance
     * @return Player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
