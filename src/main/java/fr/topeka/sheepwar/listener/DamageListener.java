package fr.topeka.sheepwar.listener;

import fr.topeka.sheepwar.arena.Arena;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.topeka.sheepwar.SheepWar;

public class DamageListener implements Listener{

	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			SheepWar instance = SheepWar.getInstance();
			Player victim = (Player) event.getEntity();
			if(instance._playerInArena.containsKey(victim)) {
				Arena a = instance._arenaList.get(instance._playerInArena.get(victim));
				if(a._playerInLobby.contains(victim)) {
					event.setCancelled(true);
					return;
				}
				if(victim.getHealth() + event.getDamage() < 0) {
					a.eliminate(victim);
				}
			}
		}
	}
	
	// also pve
	@EventHandler(ignoreCancelled = true)
	public void onPvp(EntityDamageByEntityEvent event) {
		Entity damagerEntity = event.getDamager();
		if(event.getEntity() instanceof Player) {
			Player victim = (Player) event.getEntity();
			SheepWar instance = SheepWar.getInstance();
			if(instance._playerInArena.containsKey(victim)) {
				
			}
		}
	}
}
