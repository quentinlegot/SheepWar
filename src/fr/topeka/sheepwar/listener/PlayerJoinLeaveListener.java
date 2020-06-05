package fr.topeka.sheepwar.listener;

import org.bukkit.event.Listener;

import fr.topeka.sheepwar.SheepWar;

public class PlayerJoinLeaveListener implements Listener {

	private SheepWar _instance;

	public PlayerJoinLeaveListener(SheepWar instance) {
		this._instance = instance;
	}

}
