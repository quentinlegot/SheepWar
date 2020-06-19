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
	public List<Player> _playersAlive = new ArrayList<>();
	public HashMap<Player, ItemStack[]> _playerInventory = new HashMap<>();
	public HashMap<Player, ItemStack[]> _playerArmor = new HashMap<>();
	public HashMap<Player, String> teams = new HashMap<>();
	public List<SpawnLocation> spawns;
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
	}
	public Arena(String name) {
		this._Name = name;
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
		SheepWar.getInstance().playerInArena.remove(player);
		player.sendMessage("You have left the arena");
	}
	
	public void launchingGameTask() {
		for (Player p : _playerInArena.keySet()) {
			p.resetTitle();
			p.sendTitle("Starting the game", "in " + timeBeforeStart + " seconds", 20, 20, 20);
		}
		_state = StateArena.STARTING;
		LaunchingGameTask task = new LaunchingGameTask(this);
		task.runTaskTimerAsynchronously(SheepWar.getInstance(), 0, 20);
	}
	
	public void playingGameTask() {
		_state = StateArena.PLAYING;
		PlayingGameTask task = new PlayingGameTask(this);
		task.runTaskTimerAsynchronously(SheepWar.getInstance(), 0, 20);
	}
	
	public void startGame() {
		if(_playerInArena.size() >= _minSize && _state == StateArena.WAITING)
			launchingGameTask();
	}
	
	public @Nullable Player checkWin() {
		
		return null;
	}
	
	public void cancelledGame() {
		for (Player p : _playerInArena.keySet()) {
			p.resetTitle();
			p.sendTitle("game cancelled", "No enough player in game", 20, 40, 20);
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
	
}
