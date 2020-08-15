package fr.topeka.sheepwar.commands.arena;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandArenaList extends AbstractCommand {

	public CommandArenaList(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Permission(_permission = "sheepwar.arena.list")
	@Override
	public boolean handle() {
		player.sendMessage("Arena list: ");
		for(String s : _instance._arenaList.keySet()) {
			player.sendMessage(s);
		}
		return true;
	}

	@Override
	public void commandUsage() {
		player.sendMessage("/sw arena list");
	}

}
