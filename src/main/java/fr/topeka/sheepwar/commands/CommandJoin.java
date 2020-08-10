package fr.topeka.sheepwar.commands;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;

public class CommandJoin extends AbstractCommand {

	
	public CommandJoin(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Permission(_permission = "sheepwar.join")
	@Override
	public boolean handle() {
		if(nArgs > 1) {
			if(!_instance.playerInArena.containsKey(player)){
				if(_instance._arenaList.containsKey(args[1].toLowerCase())) {
					if(_instance._arenaList.get(args[1]).playerJoin(player)) {
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

	@Override
	public void commandUsage() {
		player.sendMessage("/sw join <arena_name>");		
	}

}
