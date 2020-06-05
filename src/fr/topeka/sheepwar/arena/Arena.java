package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Arena {

	public List<Player> playerInArena = new ArrayList<>();
	public StateArena state = StateArena.WAITING;
	
	public Arena(StateArena state) {
		this.state = state;
	}
}
