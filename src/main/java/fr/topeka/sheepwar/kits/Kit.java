package fr.topeka.sheepwar.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit {

	private List<ItemStack[]> inventory = new ArrayList<>();
	private final String name;
	
	public Kit(String name, ItemStack[] inventory) {
		this.name = name;
		this.inventory.add(inventory);
	}
	
	public void addInventory(ItemStack[] inventory) {
		
	}
	
	public void setInventoryToPlayer(Player p, int level) {
		if(inventory.size() < level - 1) {
			level = 1;
		}
		p.getInventory().setContents(inventory.get(level - 1));
	}
	
	public String getName() {
		return name;
	}
	
	public int numberOfLevel() {
		return inventory.size();
	}
	
}
