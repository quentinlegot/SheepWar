package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Arena {

	public List<Player> _playerInArena = new ArrayList<>();
	public StateArena _state = StateArena.WAITING;
	public String _Name;
	
	public Arena(String name, StateArena state) {
		this._Name = name;
		this._state = state;
	}
}
