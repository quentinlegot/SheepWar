package fr.topeka.sheepwar.commands;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;

public abstract class AbstractCommand {

	protected int nArgs;
	protected Player player;
	protected String label;
	protected String[] args;
	protected SheepWar _instance;
	
	public AbstractCommand(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		this._instance = instance;
		this.player = player;
		this.label = label;
		this.args = args;
		this.nArgs = nArgs;
	}
	
	public abstract boolean handle();
	
}
