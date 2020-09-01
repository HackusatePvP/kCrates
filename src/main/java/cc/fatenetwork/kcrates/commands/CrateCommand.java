package cc.fatenetwork.kcrates.commands;

import cc.fatenetwork.kcrates.Crate;
import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrateCommand implements CommandExecutor {
    private final Crate plugin;

    public CrateCommand(Crate plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
        player.openInventory(plugin.getGuiHandler().getCrateGui().getCrateInv(profile));
        return false;
    }
}
