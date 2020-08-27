package fr.topeka.sheepwar.commands.kits;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.kits.Kit;

public class CommandKits extends AbstractCommand {

	public CommandKits(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(nArgs > 1) {
			switch(args[1].toUpperCase()) {
			case "ADD":
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
							kit.addInventory(player.getInventory().getContents());
							return true;
						}
						player.sendMessage("Kit doesn't exist");
						return false;
					}
				}
				break;
			case "LIST":
				int i = 1;
				for(Kit kit : _instance.kitList.values()) {
					player.sendMessage(i + ":" + kit.getName() + ": " + kit.numberOfLevel());
					i++;
				}
				player.sendMessage("Numbers of kits: " + i);
				// list every kits and numbers of level
				break;
			case "EDIT":
				
				// /sw kits edit <kit_name> [level]
				// level 1 if [level] don't exist
				break;
			case "REMOVE":
				if(args[2].toUpperCase().equals("KIT")) {
					// /sw kits remove kit <kit_name>
				}
				if(args[2].toUpperCase().equals("LEVEL")) {
					// /sw kits remove level <kit_name> <level>
					// if <kit_name> have no more level inventory, delete kit instance
				}
				break;
			default:
				commandUsage();
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
