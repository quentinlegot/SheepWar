package fr.topeka.sheepwar.commands;

import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.admin.CommandAdmin;

public class Commandhandler implements CommandExecutor {

	private SheepWar _instance;

	public Commandhandler(SheepWar instance) {
		this._instance = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		AbstractCommand CommandObj = null;
		if(sender instanceof Player) {
			Player player = (Player) sender;
			int nArgs = args.length;
			if(nArgs > 0) {
				if(args[0].toUpperCase().equals("JOIN")) {
					CommandObj = new CommandJoin(_instance, player, label, args, nArgs);
				}
				if(args[0].toUpperCase().equals("LEAVE")) {
					CommandObj = new Commandleave(_instance, player, label, args, nArgs);
				}
				if(args[0].toUpperCase().equals("ADMIN")) {
					CommandObj = new CommandAdmin(_instance, player, label, args, nArgs);
				}
			}
			
			
			if(CommandObj != null) {
				try {
					Method method = CommandObj.getClass().getMethod("handle");
					Permission permission = null;
					if(method.isAnnotationPresent(Permission.class)) {
						permission = method.getAnnotation(Permission.class);
					}
					if(permission == null || player.hasPermission(permission._permission())) {
						return CommandObj.handle();
					}
					return false;
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
					player.sendMessage(ChatColor.RED + "An error occured while trying to perform command");
				}
			}
		}
		sender.sendMessage("This is a player-only command");
		return false;
	}

}
