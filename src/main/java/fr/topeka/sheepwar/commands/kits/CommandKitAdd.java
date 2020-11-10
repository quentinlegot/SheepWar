package fr.topeka.sheepwar.commands.kits;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;
import fr.topeka.sheepwar.kits.Kit;

@CommandDeclaration(
		command = "ADD",
		usage = "/sw kit add <kit/level> <name>",
		permission = "sheepwar.kit.add"
		)
public class CommandKitAdd extends AbstractCommand {

	public CommandKitAdd(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(args[2].toUpperCase().equals("KIT")) {
				// /sw kits add kit <name>
				// copy sender inventory for level 1
				if(nArgs > 3) {
					if(!_instance.kitList.containsKey(args[3])) {
						_instance.kitList.put(args[3], new Kit(args[3], player.getInventory().getContents()));
						return true;
					}
					player.sendMessage("Kit already exist");
					return false;
				}
				player.sendMessage("Not enough arguments");
				return false;
			}
			if(args[2].toUpperCase().equals("LEVEL")) {
				// /sw kits add level <kit_name>
				// copy sender inventory
				Kit kit = _instance.kitList.get(args[3]);
				if(kit != null) {
					kit.addInventory(player.getInventory().getContents().clone());
					player.sendMessage("Inventory added to kit " + kit.getName() + " as level " + (kit.numberOfLevel() - 1));
					return true;
				}
				player.sendMessage("Kit doesn't exist");
				return false;
			}
		}
		return false;
	}

}
