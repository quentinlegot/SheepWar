package fr.topeka.sheepwar.commands.arena;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommmandAdminInfo extends AbstractCommand {

	public CommmandAdminInfo(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.arena.info")
	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(_instance._arenaList.containsKey(args[2])) {
				Arena a = _instance._arenaList.get(args[2]);
				player.sendMessage(a + "'s informations:");
				player.sendMessage("Lobby Location: X=" + a.lobby.X + ", Y=" + a.lobby.Y + ", Z=" + a.lobby.Z + ", Yaw=" + a.lobby.Yaw + ", Pitch=" + a.lobby.Pitch);
				player.sendMessage("Current state: " + a._state.toString());
				player.sendMessage("Player in Arena: " + a._playerInArena.size());
				player.sendMessage("Numbers of spawn:" + a.spawns.size() + "/" + a._maxSize);
				player.sendMessage("minimum players/maximum: " + a._minSize + "/" + a._maxSize);
			}
		}
		return false;
	}

}
