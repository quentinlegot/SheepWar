package fr.topeka.sheepwar.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.topeka.sheepwar.SheepWar;

public class playerBlocksListener implements Listener {

	private SheepWar _instance;

	public playerBlocksListener(SheepWar instance) {
		_instance = instance;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if(event.isCancelled()) return;
		Player player = event.getPlayer();
		if(_instance._playerOnLobby.contains(player)) {
			event.setCancelled(true);
			return;
		}
	}
	
	public void onPlace(BlockPlaceEvent event) {
		if(event.isCancelled()) return;
		Player player = event.getPlayer();
		if(_instance._playerOnLobby.contains(player)) {
			event.setCancelled(true);
			return;
		}
	}

}
