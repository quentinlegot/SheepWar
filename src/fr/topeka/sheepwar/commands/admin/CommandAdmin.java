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
			if(args[1].toUpperCase().equals("LIST")) {
				CommandObj = new CommandAdminList(_instance, player, label, args, nArgs);
			}
			if(args[1].toUpperCase().equals("INFO")) {
				
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
		return false;
	}

}
