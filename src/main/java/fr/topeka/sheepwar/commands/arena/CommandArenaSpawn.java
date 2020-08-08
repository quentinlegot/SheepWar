package fr.topeka.sheepwar.commands.arena;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandArenaSpawn extends AbstractCommand {

	public CommandArenaSpawn(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Permission(_permission = "sheepwar.arena.spawn")
	@Override
	public boolean handle() {
		// TODO Auto-generated method stub
		// /sw arena spawn <arena_name> add <team>
		// /sw arena spawn <arena_name> list
		// /sw arena spawn <arena_name> remove <team> <number>
		return false;
	}

}
