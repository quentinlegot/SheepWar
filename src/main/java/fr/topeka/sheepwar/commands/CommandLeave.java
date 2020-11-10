package fr.topeka.sheepwar.commands;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;

@CommandDeclaration(
		command = "LEAVE",
		usage = "/sw leave",
		permission = "sheepwar.leave"
		)
public class CommandLeave extends AbstractCommand {

	public CommandLeave(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

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

}
