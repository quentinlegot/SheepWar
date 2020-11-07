package fr.topeka.sheepwar.commands.kits;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

@CommandDeclaration(
		command = "REMOVE",
		usage = "/sw kit remove <kit/level> <name> [level]",
		permission = "sheepwar.kit.remove"
		)
public class CommandKitRemove extends AbstractCommand {

	public CommandKitRemove(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(args[2].toUpperCase().equals("KIT")) {
			// /sw kits remove kit <kit_name>
		}
		if(args[2].toUpperCase().equals("LEVEL")) {
			// /sw kits remove level <kit_name> <level>
			// if <kit_name> have no more level inventory, delete kit instance
		}
		return false;
	}

}
