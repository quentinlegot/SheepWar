package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Arena {

	public List<Player> _playerInArena = new ArrayList<>();
	public StateArena _state = StateArena.WAITING;
	
	public Arena(StateArena state) {
		this.state = state;
	}
}
