package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Team {

	public List<Player> _players = new ArrayList<>();
	public List<Player> _playersAlive = new ArrayList<>();
	public List<SpawnLocation> spawns = new ArrayList<>();
	public String _name;
	private Arena linkedArena;
	
	public Team(String name, Arena arena) {
		_name = name;
		linkedArena = arena;
	}
	
	public boolean addSpawn(Location location) {
		if(spawns.size() + 1 < linkedArena._maxSize / linkedArena.teams.size()) {
			spawns.add(new SpawnLocation(location));
			return true;
		}
		return false;
	}
	
	public void removePlayer(Player p) {
		if(_players.contains(p))
			_players.remove(p);
		if(_playersAlive.contains(p))
			_playersAlive.remove(p);
	}
}
