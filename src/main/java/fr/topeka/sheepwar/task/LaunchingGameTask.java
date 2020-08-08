package fr.topeka.sheepwar.task;

import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.arena.StateArena;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LaunchingGameTask extends BukkitRunnable {

	private Arena _arena;
	private int _time;
	
	public LaunchingGameTask(Arena arena) {
		_arena = arena;
		_time = _arena.timeBeforeStart;
	}

	@Override
	public void run() {
		if(_arena._state != StateArena.STARTING || _arena._playerInArena.size() >= _arena._minSize) {
			if(_time < 6) {
				if(_time == 0) {
					cancel();
					_arena.playingGameTask();
					return;
				}
				for(Player p : _arena._playerInArena.keySet())
					p.sendMessage("Game starting in " + _time + " seconds");
			}
			_time--;
			return;
		}
		_arena.cancelledGame();
		cancel();
		
	}

}
