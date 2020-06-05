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
		// TODO Auto-generated method stub
		return false;
	}

}
