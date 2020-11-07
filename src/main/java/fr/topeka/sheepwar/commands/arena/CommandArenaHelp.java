package fr.topeka.sheepwar.commands.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

@CommandDeclaration(
		command = "HELP",
		usage = "/sw arena help",
		permission = "sheepwar.arena.help"
		)
public class CommandArenaHelp extends AbstractCommand {

	
	private static List<String[]> pages;
	
	public CommandArenaHelp(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		if(pages == null) {
			pages = new ArrayList<>();
		}
	}

	@Override
	public boolean handle() {
		int page = 1;
		if(nArgs > 2 && SheepWar.isInteger(args[2])) {
			page = Integer.parseInt(args[2]);
		}
		player.sendMessage("Arena Admin help panel:");
		if(page < pages.size() && page > 0) {
			player.sendMessage(pages.get(page - 1));
		}
		player.sendMessage("This page doesn't exist");
		return true;
	}


}
