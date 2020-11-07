package fr.topeka.sheepwar.commands.arena;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.javatuples.Triplet;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;
import fr.topeka.sheepwar.commands.CommandHandler;

@CommandDeclaration(
		command = "ARENA",
		usage = "/sw arena help"
		)
public class CommandArena extends AbstractCommand {

	private ArrayList<Triplet<Class<? extends AbstractCommand>, Constructor<? extends AbstractCommand>, CommandDeclaration>> commands;
	
	public CommandArena(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		registerCommand(CommandArenaCreate.class);
		registerCommand(CommandArenaList.class);
		registerCommand(CommandArenaHelp.class);
		registerCommand(CommandArenaRemove.class);
		registerCommand(CommandArenaSchem.class);
		registerCommand(CommandArenaSpawn.class);
		registerCommand(CommandArenaState.class);
		registerCommand(CommandArenaState.class);
		registerCommand(CommandArenaInfo.class);
	}

	@Override
	public boolean handle() {
		AbstractCommand commandObj = null;
		try {
			if(nArgs > 1) {
				String cmd = args[1].toUpperCase();
				for(Triplet<Class<? extends AbstractCommand>, Constructor<? extends AbstractCommand>, CommandDeclaration> command1 : commands) {
					String permission = command1.getValue2().permission();
					if(IsGoodCommand(cmd, command1.getValue2()) && (permission == null || player.hasPermission(permission))) {
						commandObj = command1.getValue1().newInstance(_instance, player, label, args, nArgs);
						if(!commandObj.handle()) {
							player.sendMessage(command1.getValue2().usage());
							player.sendMessage(command1.getValue2().description());
						}
						return true;
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private void registerCommand(Class<? extends AbstractCommand> clazz) {
		CommandHandler.registerCommand(commands, clazz);
	}
	
	private boolean IsGoodCommand(String cmd, CommandDeclaration declaration) {
		return CommandHandler.IsGoodCommand(cmd, declaration);
	}
	
	

}
