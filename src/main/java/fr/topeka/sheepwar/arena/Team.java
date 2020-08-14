package fr.topeka.sheepwar.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Team {

	public List<Player> _players = new ArrayList<>();
	public List<Player> _playersAlive = new ArrayList<>();
	public List<SpawnLocation> spawns = new ArrayList<>();
	public ItemStack[] armor = new ItemStack[4];
	public String _name;
	private Arena linkedArena;
	
	private static Material[] armorItem = { Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET };
	
	public Team(String name, Color color,  Arena arena) {
		_name = name;
		int i = 0;
		for(Material material : armorItem) {
			ItemStack item = new ItemStack(material);
			LeatherArmorMeta itemMeta = ((LeatherArmorMeta) item.getItemMeta());
			itemMeta.setColor(color);
			item.setItemMeta(itemMeta);
			armor[i] = item;
			i++;
		}
		linkedArena = arena;
	}
	
	public boolean addSpawn(Location location) {
		if(spawns.size() + 1 < linkedArena._maxSize / linkedArena.teams.size()) {
			spawns.add(new SpawnLocation(location));
			return true;
		}
		return false;
	}
	
	public boolean removeSpawn(int index) {
		try {
			spawns.remove(index);
			return true;
		} catch (UnsupportedOperationException | IndexOutOfBoundsException  e) {
			return false;
		}
	}
	
	public void removePlayer(Player p) {
		if(_players.contains(p))
			_players.remove(p);
		if(_playersAlive.contains(p))
			_playersAlive.remove(p);
	}
}
