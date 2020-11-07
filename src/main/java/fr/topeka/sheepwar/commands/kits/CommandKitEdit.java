package fr.topeka.sheepwar.commands.kits;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

@CommandDeclaration(
		command = "EDIT",
		usage = "/sw kit edit <name> [level]",
		permission = "sheepwar.kit.edit"
		)
public class CommandKitEdit extends AbstractCommand {

	public CommandKitEdit(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		// /sw kits edit <kit_name> [level]
		// level 1 if [level] don't exist
		return false;
	}

}
