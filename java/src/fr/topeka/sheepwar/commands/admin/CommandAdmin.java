package fr.topeka.sheepwar.commands.admin;

import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandAdmin extends AbstractCommand {

	public CommandAdmin(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean handle() {
		AbstractCommand CommandObj = null;
		if(nArgs > 1) {
			if(args[1].toUpperCase().equals("LIST"))
				CommandObj = new CommandAdminList(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("INFO"))
				CommandObj = new CommmandAdminInfo(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("CREATE"))
				CommandObj = new CommandAdminCreate(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("REMOVE"))
				CommandObj = new CommandAdminRemove(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("STATE"))
				CommandObj = new CommandAdminState(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("SHEM") || args[1].toUpperCase().equals("SCHEMATIC"))
				CommandObj = new CommandAdminSchem(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("SPAWN"))
				CommandObj = new CommandAdminSpawn(_instance, player, label, args, nArgs);
			if(args[1].toUpperCase().equals("HELP"))
				CommandObj = new CommandAdminHelp(_instance, player, label, args, nArgs);
			if(CommandObj == null)
				CommandObj = new CommandAdminNull(_instance, player, label, args, nArgs);
		}else {
			CommandObj = new CommandAdminNull(_instance, player, label, args, nArgs);
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

}
