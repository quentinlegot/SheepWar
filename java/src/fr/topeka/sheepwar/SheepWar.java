package fr.topeka.sheepwar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.commands.Commandhandler;
import fr.topeka.sheepwar.listener.PlayerJoinLeaveListener;
import fr.topeka.sheepwar.listener.playerBlocksListener;

/**
 * 
 * @author Topeka_
 */
public class SheepWar extends JavaPlugin{
	
	public List<Player> _playerOnLobby = new ArrayList<>();
	public HashMap<String, Arena> _arenaList = new HashMap<>();
	public HashMap<Player, String> playerInArena = new HashMap<>();
	private static SheepWar _instance;
	public WorldEditPlugin WE;
	
	@Override
	public void onEnable() {
		_instance = this;
		WE = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		Commandhandler Commandhandler = new Commandhandler(_instance);
		getCommand("sheepwar").setExecutor(Commandhandler);
		getCommand("sheepwar").setTabCompleter(Commandhandler);
		
		List<String> aliases = new ArrayList<>();
		aliases.add("sw");
		getCommand("sheepwar").setAliases(aliases);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoinLeaveListener(_instance), _instance);
		pm.registerEvents(new playerBlocksListener(_instance), _instance);
	}
	
	public static SheepWar getInstance() {
		return _instance;
	}
	
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
