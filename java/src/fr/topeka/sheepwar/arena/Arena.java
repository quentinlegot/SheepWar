package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.task.LaunchingGameTask;
import fr.topeka.sheepwar.task.PlayingGameTask;

public class Arena {

	public HashMap<Player, Location> _playerInArena = new HashMap<>();
	public HashMap<Player, Team> _playerTeam = new HashMap<>();
	public HashMap<Player, ItemStack[]> _playerInventory = new HashMap<>();
	public HashMap<Player, ItemStack[]> _playerArmor = new HashMap<>();
	public List<SpawnLocation> spawnsRed;
	public List<SpawnLocation> spawnsBlue;
	public HashMap<String, Team> teams = new HashMap<>();
	public SpawnLocation lobby;
	public StateArena _state = StateArena.MAINTENANCE;
	public int _minSize = 2;
	public int _maxSize = 20;
	public int timeBeforeStart = 10;
	public int timeBeforeQuit = 5;
	public int gameDuration = 300;
	public String _Name;
	
	public Arena(String name, StateArena state) {
		this._Name = name;
		this._state = state;
		teams.put("RED", new Team("RED"));
		teams.put("BLUE", new Team("BLUE"));
	}

	public boolean playerJoin(Player player) {
		if(!(_state == StateArena.WAITING || _state == StateArena.STARTING) && (_playerInArena.size() >= _maxSize || _playerInArena.containsKey(player)))
			return false;
		_playerInArena.put(player, player.getLocation());
		_playerInventory.put(player, player.getInventory().getContents());
		_playerArmor.put(player, player.getInventory().getArmorContents());
		SheepWar.getInstance().playerInArena.put(player, _Name);
		player.getInventory().clear();
		player.teleport(lobby.toLocation());
		startGame();
		return true;
	}
	
	public void playerLeave(Player player) {
		player.teleport(_playerInArena.get(player));
		_playerInArena.remove(player);
		player.getInventory().setContents(_playerInventory.get(player));
		_playerInventory.remove(player);
		player.getInventory().setArmorContents(_playerArmor.get(player));
		_playerArmor.remove(player);
		for(Team t : teams.values()) {
			if(t._players.contains(player))
				t._players.remove(player);
			if(t._playersAlive.contains(player))
				t._playersAlive.remove(player);
		}
		if(_playerTeam.containsKey(player))
			_playerTeam.remove(player);
		
		SheepWar.getInstance().playerInArena.remove(player);
		
	}
	
	public void playerLeave(Player player, String message) {
		playerLeave(player);
		player.sendMessage(message);
	}
	
	public void launchingGameTask() {
		for (Player p : _playerInArena.keySet()) {
			p.resetTitle();
			p.sendTitle("Starting the game", "in " + timeBeforeStart + " seconds", 20, 20, 20);
		}
		setState(StateArena.STARTING);
		LaunchingGameTask task = new LaunchingGameTask(this);
		task.runTaskTimerAsynchronously(SheepWar.getInstance(), 0, 20);
	}
	
	public void playingGameTask() {
		setState(StateArena.PLAYING);
		for(Player player : _playerInArena.keySet()) {
			if(!_playerTeam.containsKey(player)) {
				setPlayerToRandomTeam(player);
			}
		}
		PlayingGameTask task = new PlayingGameTask(this);
		task.runTaskTimerAsynchronously(SheepWar.getInstance(), 0, 20);
	}
	
	public void setPlayerToRandomTeam(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean setPlayerToTeam(Player player, String key) {
		if(teams.containsKey(key)) {
			if(_playerTeam.containsKey(player)) {
				_playerTeam.get(player).removePlayer(player);
			}
			teams.get(key)._players.add(player);
			return true;
		}
		return false;
	}

	private void finishGameTask() {
		for(Team t : teams.values()) {
			t._players.clear();
			t._playersAlive.clear();
		}
		_playerTeam.clear();
		for(Player p : _playerInArena.keySet()) {
			playerLeave(p);
		}
	}
	
	public void startGame() {
		if(_playerInArena.size() >= _minSize && _state == StateArena.WAITING)
			launchingGameTask();
	}
	
	public void checkWin() {
		List<String> teamsStillAlive = new ArrayList<>();
		for(Team t : teams.values()) {
			if(t._playersAlive.size() != 0) {
				teamsStillAlive.add(t._name);
			}
		}
		if(teamsStillAlive.size() == 1) {
			Team winners = teams.get(teamsStillAlive.get(0));
			for (Player winner : winners._players) {
				winner.resetTitle();
				winner.sendTitle("You're win", null, 20, 60, 20);
			}
			finishGameTask();
		}
		if(teamsStillAlive.size() == 0) {
			finishGameTask();
		}
	}
	
	@SuppressWarnings("unused")
	private void regenArena() {
		setState(StateArena.LOADING);
		// TODO Auto-generated method stub
		
	}
	public void cancelledGame() {
		setState(StateArena.WAITING);
		for (Player p : _playerInArena.keySet()) {
			p.resetTitle();
			p.sendTitle("game cancelled", "No enough player in game", 20, 50, 20);
			p.sendMessage("Game cancelled, not enough player in game");
			p.sendMessage("sending you back to arena lobby, waiting for players to begin the game");
			p.teleport(lobby.toLocation());
		}
	}
	public void eliminate(Player victim) {
		victim.resetTitle();
		victim.sendTitle("You're died", null, 10, 70, 20);
		victim.setHealth(20);
		victim.setSaturation(20);
		victim.setFoodLevel(20);
		checkWin();
	}
	
	public boolean setState(StateArena state) {
		if(state == StateArena.WAITING) {
			if(spawnsBlue.size() > 0 && spawnsRed.size() > 0) {
				return false;
			}
		}
		_state = state;
		return true;
	}
	
}
