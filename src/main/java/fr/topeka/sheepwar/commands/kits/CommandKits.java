package fr.topeka.sheepwar.commands.kits;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;

public class CommandKits extends AbstractCommand {

	public CommandKits(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(nArgs > 1) {
			switch(args[1].toUpperCase()) {
			case "NEW":
				break;
			case "LIST":
				break;
			case "REMOVE":
				break;
			default:
				break;
			}
		}
		return false;
	}

	@Override
	public void commandUsage() {
		// TODO Auto-generated method stub

	}

}
