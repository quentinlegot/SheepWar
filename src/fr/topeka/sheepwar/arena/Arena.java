package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class Arena {

	public List<Player> _playerInArena = new ArrayList<>();
	public HashMap<Player, String> teams = new HashMap<>();
	public List<SpawnLocation> spawns;
	public SpawnLocation lobby;
	public StateArena _state = StateArena.MAINTENANCE;
	public int _size = 20;
	public String _Name;
	
	public Arena(String name, StateArena state) {
		this._Name = name;
		this._state = state;
	}
	public Arena(String name) {
		this._Name = name;
	}

	public boolean playerJoin(Player player) {
		if(_playerInArena.size() >= _size || _playerInArena.contains(player)) {
			return false;
		}
		_playerInArena.add(player);
		return true;
	}
	
	public void playerLeave(Player player) {
		_playerInArena.remove(player);
	}
	
	public void startGame() {
		
	}
	
	public void joinArena(Player player) {
		
	}
	
	public void leaveArena(Player player) {
		
	}
	
}
