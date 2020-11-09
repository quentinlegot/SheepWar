package fr.topeka.sheepwar.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Kit {

	private final List<ItemStack[]> inventory = new ArrayList<>();
	private final String name;
	
	public Kit(String name, ItemStack[] inventory) {
		this.name = name;
		this.inventory.add(inventory);
	}
	
	public void addInventory(ItemStack[] inventory) {
		this.inventory.add(inventory);
	}

	@Deprecated
	public void setInventoryToPlayer(Player p, int level) {
		if(inventory.size() < level - 1) {
			level = 1;
		}
		p.getInventory().setContents(inventory.get(level - 1));
	}

	public void setInventory(ItemStack[] inventory, int level){
		this.inventory.set(level, inventory);
	}

	public boolean removeLevel(int level){
		if(level >= 0 && inventory.size() > level) {
			inventory.remove(level);
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}
	
	public int numberOfLevel() {
		return inventory.size();
	}

	@Nullable
	public ItemStack[] getInventory(int level) {
		return level >= 0 && inventory.size() > level ? inventory.get(level) : null;
	}
	
}
