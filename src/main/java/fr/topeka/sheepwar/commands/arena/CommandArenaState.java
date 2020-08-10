package fr.topeka.sheepwar.commands.arena;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.arena.StateArena;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandArenaState extends AbstractCommand {

	public CommandArenaState(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.arena.state")
	@Override
	public boolean handle() {
		if(nArgs > 3) {
			if(_instance._arenaList.containsKey(args[2])) {
				Arena a = _instance._arenaList.get(args[2]);
				if(args[3].toUpperCase().equals("MAINTENANCE")) {
					if(a.setState(StateArena.MAINTENANCE)) {
						player.sendMessage(a._Name + "'s state as been set to MAINTENANCE");
					}else {
						player.sendMessage("error");
					}
				}else if(args[3].toUpperCase().equals("WAITING")) {
					if(a.setState(StateArena.WAITING)) {
						player.sendMessage(a._Name + "'s state as been set to WAITING");
					}else {
						player.sendMessage("error");
					}
					
				} else {
					player.sendMessage("Possible value are MAINTENANCE or WAITING");
				}
				return true;
			}
			player.sendMessage("Arena doesn't exist");
			return true;
		}
		player.sendMessage("Not enough arguments");
		return true;
	}

	@Override
	public void commandUsage() {
		player.sendMessage("/sw arena state <MAINTENANCE/WAITING>");
		
	}

}
