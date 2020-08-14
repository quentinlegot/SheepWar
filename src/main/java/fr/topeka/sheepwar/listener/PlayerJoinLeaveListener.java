package fr.topeka.sheepwar.listener;

import fr.topeka.sheepwar.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.topeka.sheepwar.SheepWar;

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
		for(Arena a : _instance._arenaList.values()) {
			if(a._playerInArena.containsKey(player)) {
				a.eliminate(player);
				a.playerLeave(player);
			}
		}
	}
	
}
