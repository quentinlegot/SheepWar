package fr.topeka.sheepwar.commands.arena;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.arena.Team;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommmandAdminInfo extends AbstractCommand {

	public CommmandAdminInfo(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Permission(_permission = "sheepwar.arena.info")
	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(_instance._arenaList.containsKey(args[2])) {
				Arena a = _instance._arenaList.get(args[2]);
				player.sendMessage(a._Name + "'s informations:");
				player.sendMessage("Lobby Location: "
						+ "X=" + ChatColor.BLUE + a.lobby.X + ChatColor.RESET
						+ ", Y=" + ChatColor.BLUE + a.lobby.Y + ChatColor.RESET
						+ ", Z=" + ChatColor.BLUE + a.lobby.Z + ChatColor.RESET
						+ ", Yaw=" + ChatColor.YELLOW + a.lobby.Yaw + ChatColor.RESET
						+ ", Pitch=" + ChatColor.YELLOW + a.lobby.Pitch);
				if(a.world != null) {
					player.sendMessage("Schem minimun position: "
							+ "X=" + ChatColor.BLUE + a.x + ChatColor.RESET
							+ ", Y=" + ChatColor.BLUE + a.y + ChatColor.RESET
							+ ", Z=" + ChatColor.BLUE + a.z);
				}
				player.sendMessage("Current state: " + a._state.toString());
				player.sendMessage("Player in Arena: " + a._playerInArena.size());
				for(Team team : a.teams.values()) {
					player.sendMessage("Numbers of " + team._name + " spawns: " + team.spawns.size() + "/" + (a._maxSize / a.teams.size()));
				}
				player.sendMessage("minimum / maximum players: " + a._minSize + "/" + a._maxSize);
				return true;
			}
		}
		return false;
	}

	@Override
	public void commandUsage() {
		player.sendMessage("/sw arena info <arena_name>");	
	}

}
