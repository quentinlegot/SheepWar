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
		if(nArgs > 2) {
			if(!_instance.playerInArena.containsKey(player)){
				if(_instance._arenaList.containsKey(args[2].toLowerCase())) {
					_instance._arenaList.get(args[2]).joinArena(player);
				}else {
					player.sendMessage("Cannot found arena");
				}
			}
			
		}
		return false;
	}

}
