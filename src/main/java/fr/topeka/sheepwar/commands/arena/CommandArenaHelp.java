package fr.topeka.sheepwar.commands.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandArenaHelp extends AbstractCommand {

	
	private static List<String[]> pages;
	
	public CommandArenaHelp(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		if(pages == null) {
			pages = new ArrayList<>();
		}
	}

	@Permission(_permission = "sheepwar.arena.help")
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

	@Override
	public void commandUsage() {
		player.sendMessage("/sw arena help");
	}

}
