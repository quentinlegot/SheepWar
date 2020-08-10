package fr.topeka.sheepwar.commands.arena;

import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandArena extends AbstractCommand {

	public CommandArena(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		AbstractCommand CommandObj = null;
		if(nArgs > 1) {
			if(args[1].toUpperCase().equals("LIST"))
				CommandObj = new CommandArenaList(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("INFO"))
				CommandObj = new CommmandAdminInfo(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("CREATE"))
				CommandObj = new CommandArenaCreate(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("REMOVE") || args[1].toUpperCase().equals("DELETE"))
				CommandObj = new CommandArenaRemove(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("STATE"))
				CommandObj = new CommandArenaState(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("SCHEM") || args[1].toUpperCase().equals("SCHEMATIC"))
				CommandObj = new CommandArenaSchem(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("SPAWN"))
				CommandObj = new CommandArenaSpawn(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("HELP") || CommandObj == null)
				CommandObj = new CommandArenaHelp(_instance, player, label, args, nArgs);
		}else {
			CommandObj = new CommandArenaHelp(_instance, player, label, args, nArgs);
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
				player.sendMessage(ChatColor.RED + "You haven't permission to execute this command");
				return true;
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				player.sendMessage(ChatColor.RED + "An error occured while trying to perform command");
			}
		}
		return false;
	}

	@Override
	public void commandUsage() {
		player.sendMessage("/sw arena help");
		
	}

}
