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
		// TODO Auto-generated method stub
		return false;
	}

}
