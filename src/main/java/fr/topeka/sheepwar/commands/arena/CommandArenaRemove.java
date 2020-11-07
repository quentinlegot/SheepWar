package fr.topeka.sheepwar.commands.arena;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

@CommandDeclaration(
		command = "REMOVE",
		usage = "/sw arena remove <arena_name>",
		permission = "sheepwar.arena.remove"
		)
public class CommandArenaRemove extends AbstractCommand {

	public CommandArenaRemove(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(_instance._arenaList.containsKey(args[2])) {
				_instance._arenaList.remove(args[2]);
				return true;
			}else {
				player.sendMessage("Unable to found this named arena");
				return true;
			}
		}
		player.sendMessage("No enough arguments");
		return true;
	}

}
