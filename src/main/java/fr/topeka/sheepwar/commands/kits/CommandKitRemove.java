package fr.topeka.sheepwar.commands.kits;

import fr.topeka.sheepwar.kits.Kit;
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

	public CommandKitRemove(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(args[2].toUpperCase().equals("KIT")) {
			// /sw kits remove kit <kit_name>
			if(nArgs > 3){
				if(_instance.kitList.remove(args[3]) != null){
					player.sendMessage("Kit deleted");
					return true;
				}
				player.sendMessage("Kit " + args[3] + "doesn't exist");
				return false;
			}
			player.sendMessage("Not enough arguments");
			return false;
		}
		if(args[2].toUpperCase().equals("LEVEL")) {
			// /sw kits remove level <kit_name> <level>
			// if <kit_name> have no more level inventory, delete kit instance
			if(nArgs > 4) {
				Kit kit = _instance.kitList.get(args[3]);
				if(kit != null){
					try{
						if(kit.removeLevel(Integer.parseInt(args[4]))){
							player.sendMessage("level deleted");
							if(kit.numberOfLevel() == 0){
								_instance.kitList.remove(kit.getName());
								player.sendMessage("Kit has been deleted because there is no more level for this kit");
							}
							return true;
						}
						player.sendMessage(args[4] + " is an invalid integer");
						return false;
					} catch(NumberFormatException e){
						player.sendMessage(args[4] + " isn't an integer");
						return false;
					}
				}
				player.sendMessage("Kit " + args[3] + "doesn't exist");
				return false;
			}
			player.sendMessage("Not enough arguments");
			return false;
		}
		return false;
	}

}
