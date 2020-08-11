package fr.topeka.sheepwar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.commands.Commandhandler;
import fr.topeka.sheepwar.listener.DamageListener;
import fr.topeka.sheepwar.listener.PlayerJoinLeaveListener;
import fr.topeka.sheepwar.listener.playerBlocksListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.primesoft.asyncworldedit.api.IAsyncWorldEdit;
import org.primesoft.asyncworldedit.api.blockPlacer.IBlockPlacer;
import org.primesoft.asyncworldedit.api.playerManager.IPlayerManager;
import org.primesoft.asyncworldedit.api.worldedit.IAsyncEditSessionFactory;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

/**
 * 
 * @author Topeka_
 */
public class SheepWar extends JavaPlugin{

	public HashMap<String, Arena> _arenaList = new HashMap<>();
	public HashMap<Player, String> _playerInArena = new HashMap<>();
	private static SheepWar _instance;
	public WorldEditPlugin WE;
	public IAsyncWorldEdit aweAPI;
	public IAsyncEditSessionFactory esFactory;
	public IBlockPlacer blockPlacer;
	public IPlayerManager playerManager;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		_instance = this;
		WE = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		aweAPI = (IAsyncWorldEdit)Bukkit.getServer().getPluginManager().getPlugin("AsyncWorldEdit");
		esFactory = (IAsyncEditSessionFactory)WorldEdit.getInstance().getEditSessionFactory();
		blockPlacer = aweAPI.getBlockPlacer();
		playerManager = aweAPI.getPlayerManager();
		Commandhandler Commandhandler = new Commandhandler(_instance);
		getCommand("sheepwar").setExecutor(Commandhandler);
		getCommand("sheepwar").setTabCompleter(Commandhandler);
		
		List<String> aliases = new ArrayList<>();
		aliases.add("sw");
		getCommand("sheepwar").setAliases(aliases);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoinLeaveListener(_instance), _instance);
		pm.registerEvents(new DamageListener(), _instance);
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
