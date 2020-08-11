package fr.topeka.sheepwar.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;

public class playerBlocksListener implements Listener {

	private SheepWar _instance;

	public playerBlocksListener(SheepWar instance) {
		_instance = instance;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBreak(BlockBreakEvent event) {
		if(event.isCancelled()) return;
		Player player = event.getPlayer();
		if(_instance._playerInArena.containsKey(player)) {
			Arena arena = _instance._arenaList.get(_instance._playerInArena.get(player));
			if(arena._playerInLobby.contains(player)) {
				event.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlace(BlockPlaceEvent event) {
		if(event.isCancelled()) return;
		Player player = event.getPlayer();
		if(_instance._playerInArena.containsKey(player)) {
			Arena arena = _instance._arenaList.get(_instance._playerInArena.get(player));
			if(arena._playerInLobby.contains(player)) {
				event.setCancelled(true);
				return;
			}
		}
	}

}
