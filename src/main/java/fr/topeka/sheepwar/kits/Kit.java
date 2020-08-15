package fr.topeka.sheepwar.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Kit {

	protected List<ItemStack[]> inventory = new ArrayList<>();
	
	public Kit(ItemStack[] inventory) {
		this.inventory.add(inventory);
	}
	
	public void setInventory(Player p, int level) {
		if(inventory.size() > level) {
			p.getInventory().setContents(inventory.get(level));
		}
	}
	
	
	
}
