package fr.topeka.sheepwar.commands.arena;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

@CommandDeclaration(
		command = "LIST",
		usage = "/sw arena list",
		permission = "sheepwar.arena.list"
		)
public class CommandArenaList extends AbstractCommand {

	public CommandArenaList(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		player.sendMessage("Arena list: ");
		for(String s : _instance._arenaList.keySet()) {
			player.sendMessage(s);
		}
		return true;
	}

}
