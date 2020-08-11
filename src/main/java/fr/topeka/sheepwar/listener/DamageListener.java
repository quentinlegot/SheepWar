package fr.topeka.sheepwar.listener;

import fr.topeka.sheepwar.arena.Arena;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.topeka.sheepwar.SheepWar;

public class DamageListener implements Listener{

	@EventHandler
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
	@EventHandler
	public void onPvp(EntityDamageByEntityEvent event) {
		Entity damagerEntity = event.getDamager();
		if(event.getEntity() instanceof Player) {
			Player victim = (Player) event.getEntity();
			SheepWar instance = SheepWar.getInstance();
			if(instance._playerInArena.containsKey(victim)) {
				if(damagerEntity instanceof Player) {
					Player damager = (Player) damagerEntity;
					if(!instance._playerInArena.containsKey(damager)) {
						event.setCancelled(true);
						return;
					}
				}
				Arena a = instance._arenaList.get(instance._playerInArena.get(victim));
				if(a._playerInLobby.contains(victim)) {
					event.setCancelled(true);
					return;
				}
				if(victim.getHealth() <= event.getDamage()) {
					a.eliminate(victim);
					event.setCancelled(true);
					if(damagerEntity instanceof Arrow) {
						Player damager = (Player) ((Arrow) damagerEntity).getShooter();
						for(Player player : a._playerInArena.keySet()) {
							player.sendMessage(victim.getName() + " was killed by an arrow shot from " + damager.getName());
						}
					} else if(damagerEntity instanceof Snowball) {
						Player damager = (Player) ((Snowball) damagerEntity).getShooter();
						for(Player player : a._playerInArena.keySet()) {
							player.sendMessage(victim.getName() + " was kileld by a snowball shot from " + damager.getName());
						}
					} else if(damagerEntity instanceof Egg) {
						Player damager = (Player) ((Egg) damagerEntity).getShooter();
						for(Player player : a._playerInArena.keySet()) {
							player.sendMessage(victim.getName() + " was kileld by a egg shot from " + damager.getName());
						}
					}else {
						for(Player player : a._playerInArena.keySet()) {
							player.sendMessage(victim.getName() + " was killed by " + damagerEntity.getName());
						}
					}
					
				}
				
			}
		}
	}
}
