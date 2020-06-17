package fr.topeka.sheepwar.arena;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.task.LaunchingGameTask;

public class Arena {

	public HashMap<Player, Location> _playerInArena = new HashMap<>();
	public HashMap<Player, String> teams = new HashMap<>();
	public List<SpawnLocation> spawns;
	public SpawnLocation lobby;
	public StateArena _state = StateArena.MAINTENANCE;
	public int _minSize = 2;
	public int _maxSize = 20;
	public String _Name;
	
	public Arena(String name, StateArena state) {
		this._Name = name;
		this._state = state;
	}
	public Arena(String name) {
		this._Name = name;
	}

	public boolean playerJoin(Player player) {
		if(_playerInArena.size() >= _maxSize || _playerInArena.containsKey(player)) {
			return false;
		}
		_playerInArena.put(player, player.getLocation());
		player.teleport(lobby.toLocation());
		return true;
	}
	
	public void playerLeave(Player player) {
		player.teleport(_playerInArena.get(player));
		_playerInArena.remove(player);
		player.sendMessage("You have left the arena");
	}
	
	public void startGame() {
		LaunchingGameTask task = new LaunchingGameTask(this);
		task.runTaskTimerAsynchronously(SheepWar.getInstance(), 0, 20);
	}
	
}
