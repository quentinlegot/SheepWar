package fr.topeka.sheepwar.arena;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.primesoft.asyncworldedit.api.playerManager.IPlayerEntry;
import org.primesoft.asyncworldedit.api.utils.IFuncParamEx;
import org.primesoft.asyncworldedit.api.worldedit.ICancelabeEditSession;
import org.primesoft.asyncworldedit.api.worldedit.IThreadSafeEditSession;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.extent.inventory.BlockBag;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.task.LaunchingGameTask;
import fr.topeka.sheepwar.task.PlayingGameTask;

public class Arena {

	public HashMap<Player, Location> _playerInArena = new HashMap<>();
	public HashMap<Player, Team> _playerTeam = new HashMap<>();
	public HashMap<Player, ItemStack[]> _playerInventory = new HashMap<>();
	public HashMap<Player, ItemStack[]> _playerArmor = new HashMap<>();
	public List<Player> _playerInLobby = new ArrayList<>();
	public HashMap<String, Team> teams = new HashMap<>();
	public SpawnLocation lobby = null;
	public StateArena _state = StateArena.MAINTENANCE;
	public int _minSize = 2;
	public int _maxSize = 10;
	public int timeBeforeStart = 10;
	public int timeBeforeQuit = 5;
	public int gameDuration = 300;
	public String _Name;
	public World world = null;
	public double x, y, z;
	private SheepWar _instance;
	
	public Arena(SheepWar instance, String name, StateArena state) {
		this._instance = instance;
		this._Name = name;
		this._state = state;
		teams.put("RED", new Team("RED", this));
		teams.put("BLUE", new Team("BLUE", this));
	}

	public boolean playerJoin(Player player) {
		if(!(_state == StateArena.WAITING || _state == StateArena.STARTING) || _playerInArena.size() >= _maxSize || _playerInArena.containsKey(player))
			return false;
		_playerInArena.put(player, player.getLocation());
		_playerInventory.put(player, player.getInventory().getContents());
		_playerArmor.put(player, player.getInventory().getArmorContents());
		SheepWar.getInstance()._playerInArena.put(player, _Name);
		player.getInventory().clear();
		player.teleport(lobby.toLocation());
		_playerInLobby.add(player);
		startGame();
		return true;
	}
	
	public void setLobbyLocation(Location location) {
		lobby = new SpawnLocation(location);
	}
	
	public void playerLeave(Player player) {
		player.setGameMode(Bukkit.getDefaultGameMode());
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
		
		SheepWar.getInstance()._playerInArena.remove(player);
		checkWin();
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
		task.runTaskTimer(SheepWar.getInstance(), 0, 20);
	}
	
	public void playingGameTask() {
		setState(StateArena.PLAYING);
		for(Player player : _playerInArena.keySet()) {
			if(!_playerTeam.containsKey(player)) {
				setPlayerToRandomTeam(player);
			}
			_playerInLobby.remove(player);
			_playerTeam.get(player)._playersAlive.add(player);
			player.teleport(_playerTeam.get(player).spawns.get((int) (Math.random() * _playerTeam.get(player).spawns.size() - 1)).toLocation());
			player.setGameMode(GameMode.SURVIVAL);
			player.setHealth(20);
			player.setSaturation(20);
			player.setFoodLevel(20);
		}
		
		PlayingGameTask task = new PlayingGameTask(this);
		task.runTaskTimer(SheepWar.getInstance(), 0, 20);
	}
	
	public void setPlayerToRandomTeam(Player player) {
		Team teamWithLessPlayer = null;
		for(Team t : teams.values()) {
			if(teamWithLessPlayer == null)
				teamWithLessPlayer = t;
			else if(t._players.size() < teamWithLessPlayer._players.size())
				teamWithLessPlayer = t;
		}
		_playerTeam.put(player, teamWithLessPlayer);
		teamWithLessPlayer._players.add(player);
		
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

	public void finishGameTask() {
		for(Team t : teams.values()) {
			t._players.clear();
			t._playersAlive.clear();
		}
		_playerTeam.clear();
		List<Player> playerList = new ArrayList<>();
		for(Player p : _playerInArena.keySet()) {
			playerList.add(p);
		}
		for(Player p : playerList) {
			playerLeave(p);
		}
		regenArena(StateArena.WAITING);
	}
	
	public void startGame() {
		if(_playerInArena.size() >= _minSize && _state == StateArena.WAITING)
			launchingGameTask();
		else {
			for(Player p : _playerInArena.keySet()) {
				p.sendMessage("Waiting for player: " + _playerInArena.size() + "/" + _minSize);
			}
		}
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
	
	private void regenArena(StateArena stateWhenRegenered) {
		_state = StateArena.LOADING;
		IPlayerEntry awePlayer = _instance.playerManager.getConsolePlayer();
		System.out.println(SheepWar.getInstance().getDataFolder().getAbsolutePath() + File.separator + _Name + ".schem");
		File file = new File(SheepWar.getInstance().getDataFolder().getAbsolutePath() + File.separator + _Name + ".schem");
		if(file.exists()) {
			ClipboardFormat format = ClipboardFormats.findByFile(file);
			if(format != null) {
				com.sk89q.worldedit.world.World world = BukkitAdapter.adapt(this.world);
				int maxBlocks = -1; // can be -1
				BlockBag bb = null; // can be null
				IThreadSafeEditSession es = _instance.esFactory.getThreadSafeEditSession(world, maxBlocks, bb, awePlayer);

				_instance.blockPlacer.performAsAsyncJob(es, awePlayer, "regen" + _Name, new IFuncParamEx<Integer, ICancelabeEditSession, MaxChangedBlocksException>() {
					@Override
					public Integer execute(ICancelabeEditSession editSession) throws MaxChangedBlocksException{
						Clipboard clipboard;
						try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
							clipboard = reader.read();
							Operation operation = new ClipboardHolder(clipboard)
									.createPaste(editSession)
									.to(BlockVector3.at(x, y, z))
									.ignoreAirBlocks(false)
									.copyEntities(false)
									.build();
							Operations.completeBlindly(operation);
							System.out.println("Operation complete");
							_state = stateWhenRegenered;
							return 0;
						} catch (IOException e) {
							e.printStackTrace();
							return 1;
						}
					}
				});
				
			}
		}
		
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
		victim.setGameMode(GameMode.SPECTATOR);
		victim.setHealth(20);
		victim.setSaturation(20);
		victim.setFoodLevel(20);
		_playerTeam.get(victim)._playersAlive.remove(victim);
		checkWin();
	}
	
	public boolean setState(StateArena state) {
		if(state.equals(StateArena.WAITING)) {
			if(lobby == null)
				return false;
			for(Team team : teams.values()) {
				if(team.spawns.size() == 0) {
					return false;
				}
			}
		}
		_state = state;
		if(state.equals(StateArena.WAITING) || state.equals(StateArena.MAINTENANCE)) {
			regenArena(state);
		}
		return true;
	}
	
}
