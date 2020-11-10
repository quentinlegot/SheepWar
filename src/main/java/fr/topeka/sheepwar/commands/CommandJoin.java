package fr.topeka.sheepwar.commands;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;

@CommandDeclaration(
		command = "JOIN",
		usage = "/sw join <arena_name>",
		permission = "sheepwar.join"
		)
public class CommandJoin extends AbstractCommand {
	
	public CommandJoin(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(nArgs > 1) {
			if(!_instance._playerInArena.containsKey(player)){
				if(_instance._arenaList.containsKey(args[1])) {
					if(_instance._arenaList.get(args[1]).playerJoin(player)) {
						player.sendMessage("You joined arena " + args[1]);
						return true;
					}
					player.sendMessage("Arena is unavailable");
					return false;
				}
				player.sendMessage("Cannot found arena");
				return false;
			}
			player.sendMessage("You're already playing in an arena");
			return false;
		}
		player.sendMessage("Not enough arguments");
		return false;
	}

}
