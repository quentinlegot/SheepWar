package fr.topeka.sheepwar.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;

public class PlayerJoinLeaveListener implements Listener {

	private SheepWar _instance;

	public PlayerJoinLeaveListener(SheepWar instance) {
		this._instance = instance;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(_instance._playerOnLobby.contains(player)) {
			_instance._playerOnLobby.remove(player);
		}
		for(Arena a : _instance._arenaList.values()) {
			if(a._playerInArena.containsKey(player)) {
				a.playerLeave(player);
			}
		}
	}
	
}
