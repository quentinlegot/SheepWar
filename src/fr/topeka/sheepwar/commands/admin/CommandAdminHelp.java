package fr.topeka.sheepwar.commands.admin;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandAdminHelp extends AbstractCommand {

	public CommandAdminHelp(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.admin.help")
	@Override
	public boolean handle() {
		// TODO Auto-generated method stub
		return false;
	}

}
