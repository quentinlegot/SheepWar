package fr.topeka.sheepwar.commands.arena;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.arena.SpawnLocation;
import fr.topeka.sheepwar.arena.Team;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandArenaSpawn extends AbstractCommand {

	public CommandArenaSpawn(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Permission(_permission = "sheepwar.arena.spawn")
	@Override
	public boolean handle() {
		// TODO Auto-generated method stub
		// /sw arena spawn <arena_name> add <team>
		// /sw arena spawn <arena_name> list
		// /sw arena spawn <arena_name> remove <team> <number>
		if(nArgs > 3) {
			if(_instance._arenaList.containsKey(args[2])) {
				Arena arena = _instance._arenaList.get(args[2]);
				switch(args[3]) {
				case "add":
					if(arena.teams.containsKey(args[3])) {
						if(arena.teams.get(args[3]).addSpawn(player.getLocation())) {
							player.sendMessage("Spawn added to team" + args[3]);
							return true;
						}
						player.sendMessage(ChatColor.RED + "Max numbers of spawns has been already added to this team");
						return false;
					}
					player.sendMessage("Team doesn't exist");
					return false;
				case "list":
					for(Team team : arena.teams.values()) {
						player.sendMessage("team " + team._name + " spawns:");
						int i = 0;
						for(SpawnLocation loc : team.spawns) {
							player.sendMessage(i + ": " + loc.toLocation().toString());
							i++;
						}
					}
					return true;
				case "remove":
					break;
				default:
					break;
				}
				return false;
			}
			player.sendMessage("Arena " + args[2] + " doesn't exist");
			return false;
		}
		player.sendMessage("Not enough arguments");
		return false;
	}

}
