package fr.topeka.sheepwar.kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit {

	protected ItemStack[] inventory;
	protected ItemStack[] armor;
	
	public Kit(ItemStack[] inventory, ItemStack[] armor) {
		this.inventory = inventory;
		this.armor = armor;
	}
	
	public void setInventory(Player p) {
		p.getInventory().setContents(inventory);
		p.getInventory().setArmorContents(armor);
	}
	
	
	
}
