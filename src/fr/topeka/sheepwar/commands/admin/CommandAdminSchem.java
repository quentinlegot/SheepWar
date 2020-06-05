package fr.topeka.sheepwar.commands.admin;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandAdminSchem extends AbstractCommand {

	public CommandAdminSchem(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.admin.schem")
	@Override
	public boolean handle() {
		// TODO Auto-generated method stub
		return false;
	}

}
