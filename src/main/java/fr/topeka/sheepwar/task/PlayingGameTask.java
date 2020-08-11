package fr.topeka.sheepwar.task;

import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.arena.StateArena;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayingGameTask extends BukkitRunnable{

	private Arena _arena;
	private int _timer;

	public PlayingGameTask(Arena arena) {
		_arena = arena;
		_timer = _arena.gameDuration;
	}

	@Override
	public void run() {
		if(_arena._state != StateArena.PLAYING) {
			cancel();
			return;
		}
		if(_timer == 0) {
			_arena.finishGameTask();
			cancel();
			return;
		}
		if(_timer == 60 || _timer == 30 || _timer == 15 || _timer == 10 || _timer < 6) {
			for(Player p : _arena._playerInArena.keySet())
				p.sendMessage("Game finishing in " + _timer + " seconds");
		}
		_timer--;
	}

}
