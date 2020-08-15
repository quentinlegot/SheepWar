package fr.topeka.sheepwar.commands;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;

public class Commandleave extends AbstractCommand {

	public Commandleave(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Permission(_permission = "sheepwar.leave")
	@Override
	public boolean handle() {
		if(_instance._playerInArena.get(player) != null) {
			Arena arena = _instance._arenaList.get(_instance._playerInArena.get(player));
			arena.eliminate(player);
			arena.playerLeave(player, "You have left the arena");
			return true;
		}
		player.sendMessage("You're not currently playing SheepWar game");
		return true;
	}

	@Override
	public void commandUsage() {
		player.sendMessage("/sw leave");
	}

}
