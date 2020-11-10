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

	private static ArrayList<Triplet<Class<? extends AbstractCommand>, Constructor<? extends AbstractCommand>, CommandDeclaration>> commands = new ArrayList<>();;
	
	public CommandArena(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
		if(commands.isEmpty()) {
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
		
	}

	@Override
	public boolean handle() {
		try {
			if(nArgs > 1) {
				String cmd = args[1].toUpperCase();
				for(Triplet<Class<? extends AbstractCommand>, Constructor<? extends AbstractCommand>, CommandDeclaration> command1 : commands) {
					String permission = command1.getValue2().permission();
					if(IsGoodCommand(cmd, command1.getValue2()) && (permission == null || player.hasPermission(permission))) {
						AbstractCommand commandObj = command1.getValue1().newInstance(_instance, player, label, args, nArgs);
						if(!commandObj.handle()) {
							if(!command1.getValue2().usage().equals(""))
								player.sendMessage(command1.getValue2().usage());
							if(!(command1.getValue2().description().length == 1 && command1.getValue2().description()[0].equals("")))
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
