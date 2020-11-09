package fr.topeka.sheepwar.commands.kits;

import fr.topeka.sheepwar.kits.Kit;
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
		if(nArgs > 2) {
			try{
				Kit kit = _instance.kitList.get(args[2]);
				if(kit != null) {
					int level = 0;
					if(nArgs > 3)
						level = Integer.parseInt(args[3]) - 1;
					if(level >= 0 && level < kit.numberOfLevel()){
						kit.setInventory(player.getInventory().getContents().clone(), level);
						return true;
					}
					player.sendMessage(level + " is an invalid number");
					player.sendMessage("Kit " + kit.getName() + " have " + kit.numberOfLevel() + " level");
					return false;
				}
				player.sendMessage("Kit " + args[2] + " doesn't exist");
				return false;
			}catch (NumberFormatException e){
				player.sendMessage(args[3] + " isn't an integer");
				return false;
			}
		}
		player.sendMessage("Not enough arguments");
		return false;
	}

}
