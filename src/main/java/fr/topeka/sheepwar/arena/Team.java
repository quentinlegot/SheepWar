package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Team {

	public List<Player> _players = new ArrayList<>();
	public List<Player> _playersAlive = new ArrayList<>();
	public String _name;
	
	public Team(String name) {
		_name = name;
	}
	
	public void removePlayer(Player p) {
		if(_players.contains(p))
			_players.remove(p);
		if(_playersAlive.contains(p))
			_playersAlive.remove(p);
	}
}
