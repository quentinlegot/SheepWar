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
import fr.topeka.sheepwar.arena.SpawnLocation;
import fr.topeka.sheepwar.commands.Commandhandler;
import fr.topeka.sheepwar.listener.PlayerJoinLeaveListener;

/**
 * 
 * @author Topeka_
 */
public class SheepWar extends JavaPlugin{
	
	public List<Player> _playerOnLobby = new ArrayList<>();
	public HashMap<String, Arena> _arenaList = new HashMap<>();
	public HashMap<Player, String> playerInArena = new HashMap<>();
	private SheepWar _instance = this;
	public WorldEditPlugin WE;
	
	@Override
	public void onEnable() {
		WE = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		getCommand("sheepwar").setExecutor(new Commandhandler(_instance));
		
		List<String> aliases = new ArrayList<>();
		aliases.add("sw");
		getCommand("sheepwar").setAliases(aliases);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoinLeaveListener(_instance), _instance);
	}
	
}
