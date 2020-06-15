package fr.topeka.sheepwar.commands;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;

public class Commandleave extends AbstractCommand {

	public Commandleave(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.leave")
	@Override
	public boolean handle() {
		if(_instance.playerInArena.get(player) != null) {
			_instance._arenaList.get(_instance.playerInArena.get(player)).leaveArena(player);
			return true;
		}
		player.sendMessage("You're not currently playing SheepWar game");
		return true;
	}

}
