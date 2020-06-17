package fr.topeka.sheepwar.commands.admin;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandAdminList extends AbstractCommand {

	public CommandAdminList(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.admin.list")
	@Override
	public boolean handle() {
		player.sendMessage("Arena list: ");
		for(String s : _instance._arenaList.keySet()) {
			player.sendMessage(s);
		}
		return true;
	}

}
