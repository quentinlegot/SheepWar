package fr.topeka.sheepwar.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SpawnLocation {

	public String world;
	public double X, Y, Z;
	public float Pitch, Yaw;
	public String team;
	
	public SpawnLocation(Location location, String team) {
		world = location.getWorld().getName();
		X = location.getX();
		Y = location.getY();
		Z = location.getZ();
		Pitch = location.getPitch();
		Yaw = location.getYaw();
		this.team = team;
	}
	
	public SpawnLocation(Location location) {
		world = location.getWorld().getName();
		X = location.getX();
		Y = location.getY();
		Z = location.getZ();
		Pitch = location.getPitch();
		Yaw = location.getYaw();
	}

	public Location toLocation() {
		return new Location(Bukkit.getWorld(world), X, Y, Z, Yaw, Pitch);
	}
	
}
