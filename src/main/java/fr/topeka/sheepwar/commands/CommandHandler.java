package fr.topeka.sheepwar.commands;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.javatuples.Triplet;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.arena.CommandArena;
import fr.topeka.sheepwar.commands.kits.CommandKits;

public class CommandHandler implements CommandExecutor, TabCompleter {

	private SheepWar _instance;
	private ArrayList<Triplet<Class<? extends AbstractCommand>, Constructor<? extends AbstractCommand>, CommandDeclaration>> commands = new ArrayList<>();


	public CommandHandler(SheepWar instance) {
		this._instance = instance;
		registerCommand(CommandJoin.class);
		registerCommand(CommandLeave.class);
		registerCommand(CommandArena.class);
		registerCommand(CommandKits.class);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			AbstractCommand commandObj = null;
			Player player = (Player) sender;
			int nArgs = args.length;
			try {
				if(nArgs > 0) {
					String cmd = args[0].toUpperCase();
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
		sender.sendMessage("This is a player-only command");
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
	
	public static void registerCommand(ArrayList<Triplet<Class<? extends AbstractCommand>, Constructor<? extends AbstractCommand>, CommandDeclaration>> commands,
			Class<? extends AbstractCommand> clazz) {
		try {
			 CommandDeclaration declaration = clazz.getAnnotation(CommandDeclaration.class);
			 if(declaration == null)
				 throw new NullPointerException(clazz.getSimpleName() + " need a CommandDeclaration annnotation");
			Constructor<? extends AbstractCommand> constructor = clazz.getConstructor(SheepWar.class, Player.class, String.class, String[].class, Integer.class);
			commands.add(new Triplet<>(clazz, constructor, declaration));
		} catch (NoSuchMethodException | SecurityException | NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void registerCommand(Class<? extends AbstractCommand> clazz) {
		registerCommand(commands, clazz);
	}
	
	@SafeVarargs
	private static <T> boolean isArrayContains(T o, T... a) {
		for(T e : a) {
			if(o.equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean IsGoodCommand(String cmd, CommandDeclaration declaration) {
		return declaration.command().equals(cmd) || isArrayContains(cmd, declaration.aliases());
	}

}
