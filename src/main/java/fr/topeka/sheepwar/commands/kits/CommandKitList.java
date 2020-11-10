package fr.topeka.sheepwar.commands.kits;

import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

@CommandDeclaration(
		command = "LIST",
		usage = "/sw kit list",
		permission = "sheepwar.kit.list"
		)
public class CommandKitList extends AbstractCommand {

	public CommandKitList(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		// list every kits and numbers of level
		AtomicInteger i = new AtomicInteger();
		_instance.kitList.values().forEach(kit -> player.sendMessage(i.incrementAndGet() + ": " + kit.getName() + ": " + kit.numberOfLevel()));
		player.sendMessage("Numbers of kits: " + i.get());
		return true;
	}

}
