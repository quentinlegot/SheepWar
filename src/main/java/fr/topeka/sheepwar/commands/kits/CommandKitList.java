package fr.topeka.sheepwar.commands.kits;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;
import fr.topeka.sheepwar.kits.Kit;

@CommandDeclaration(
		command = "LIST",
		usage = "/sw kit list",
		permission = "sheepwar.kit.list"
		)
public class CommandKitList extends AbstractCommand {

	public CommandKitList(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		int i = 1;
		for(Kit kit : _instance.kitList.values()) {
			player.sendMessage(i + ":" + kit.getName() + ": " + kit.numberOfLevel());
			i++;
		}
		player.sendMessage("Numbers of kits: " + i);
		// list every kits and numbers of level
		return true;
	}

}
