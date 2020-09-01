package cc.fatenetwork.kcrates.commands;

import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kcrates.Crate;
import cc.fatenetwork.kcrates.crates.Keys;
import cc.fatenetwork.kcrates.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KeyCommand implements CommandExecutor {
    private final Crate plugin;

    public KeyCommand(Crate plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kcrate.admin")) {
            player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
            return true;
        }
        if (args.length == 0) {
            List<String> message = new ArrayList<>();
            message.add("&7&m-----------------------------------");
            message.add("&7* &c/key give <key> <player> [amount]");
            message.add("&7* &c/key set <key> <player> <amount>");
            message.add("&7&m-----------------------------------");
            message.forEach(msg -> player.sendMessage(StringUtil.format(msg)));
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("give")) {
                    player.sendMessage(StringUtil.format("&7* &c/key give <key> <player> [amount]"));
                } else if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(StringUtil.format("&7* &c/key set <player> <amount>"));
                } else {
                    player.sendMessage(StringUtil.format("&cArgument not found."));
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("give")) {
                    player.sendMessage(StringUtil.format("&7* &c/key give <key> <player> [amount]"));

                } else if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(StringUtil.format("&7* &c/key set <player> <amount>"));
                } else {
                    player.sendMessage(StringUtil.format("&cArgument not found."));
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("give")) {
                    String key = args[1];
                    Player target = Bukkit.getPlayerExact(args[2]);
                    if (target == null) {
                        player.sendMessage(StringUtil.format("&cTarget not found."));
                        return true;
                    }
                    if (Keys.getByName(key) == null) {
                        player.sendMessage(StringUtil.format("&cKey not found."));
                    }
                    Profile profile = plugin.getProfileManager().getProfile(target.getUniqueId());
                    plugin.getCrateInterface().addKey(key, profile);
                } else if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage("&7* &c/key set <player> <amount>");
                } else {
                    player.sendMessage(StringUtil.format("&cArgument not found."));
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("set")) {
                    String key = args[2];
                    if (!isInt(args[3])) {
                        player.sendMessage(StringUtil.format("&cNot a valid int type"));
                        return true;
                    }
                    int amount = Integer.parseInt(args[3]);
                    Player target = Bukkit.getPlayerExact(args[2]);
                    if (target == null) {
                        player.sendMessage(StringUtil.format("&cTarget not found."));
                        return true;
                    }
                    if (Keys.getByName(key) == null) {
                        player.sendMessage(StringUtil.format("&cKey not found."));
                    }
                    Profile profile = plugin.getProfileManager().getProfile(target.getUniqueId());
                    plugin.getCrateInterface().setKeys(key, profile, amount);
                } else {
                    player.sendMessage(StringUtil.format("&cArgument not found."));
                }
            }
        }
        return false;
    }

    private boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
