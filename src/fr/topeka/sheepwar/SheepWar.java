package fr.topeka.sheepwar;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.commands.Commandhandler;
import fr.topeka.sheepwar.listener.PlayerJoinLeaveListener;

public class SheepWar extends JavaPlugin{
	
	public List<Player> _playerOnLobby = new ArrayList<>();
	public List<Arena> _arenaList = new ArrayList<>();
	private SheepWar _instance = this;
	
	
	@Override
	public void onEnable() {
		getCommand("sheepwar").setExecutor(new Commandhandler(_instance));
		List<String> aliases = new ArrayList<>();
		aliases.add("sw");
		getCommand("sheepwar").setAliases(aliases);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoinLeaveListener(_instance), _instance);
	}
	
}
