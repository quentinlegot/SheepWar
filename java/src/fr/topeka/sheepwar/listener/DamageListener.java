package fr.topeka.sheepwar.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;

public class DamageListener implements Listener{

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntity().isDead() && event.getEntity() instanceof Player) {
			SheepWar instance = SheepWar.getInstance();
			Player victim = (Player) event.getEntity();
			if(instance.playerInArena.containsKey(victim)) {
				Arena a = instance._arenaList.get(instance.playerInArena.get(victim));
				event.setCancelled(true);
				a.eliminate(victim);
			}
		}
	}
}
