package fr.topeka.sheepwar.commands.kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;
import fr.topeka.sheepwar.kits.Kit;
import org.bukkit.inventory.ItemStack;

@CommandDeclaration(
		command = "INFO",
		usage = "/sw kit info <kit_name> [level]",
		permission = "sheepwar.kit.info"
		)
public class CommandKitInfo extends AbstractCommand {

	public CommandKitInfo(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		// /sw kit info <kit_name> [level]
		// level 1 if level doesn't exist
		if(nArgs > 2) {
			Kit kit = _instance.kitList.get(args[2]);
			if(kit != null) {
				try {
					int level = 0;
					if (nArgs > 3)
						level = Integer.parseInt(args[3]) - 1;
					ItemStack[] items = kit.getInventory(level);
					if(items != null){
						Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST.getDefaultSize(), kit.getName() + " level " + level);
						inv.setContents(kit.getInventory(level).clone());
						player.openInventory(inv);
						return true;
					}
					player.sendMessage(level + " is an invalid number");
					player.sendMessage("Kit " + kit.getName() + " have " + kit.numberOfLevel() + " level");
					return  false;
				} catch (NumberFormatException e) {
					player.sendMessage(args[3] + " isn't an integer");
					return false;
				}
			}
			player.sendMessage("Kit " + args[2] + " doesn't exist");
			return false;
		}
		player.sendMessage("Not enough arguments");
		return false;
		
	}

}
