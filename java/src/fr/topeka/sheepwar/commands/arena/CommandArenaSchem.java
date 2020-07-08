package fr.topeka.sheepwar.commands.arena;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;
/**
 * 
 * @see https://worldedit.enginehub.org/en/latest/api/examples/clipboard/
 */
public class CommandArenaSchem extends AbstractCommand {

	public CommandArenaSchem(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.arena.schem")
	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(_instance._arenaList.containsKey(args[2])) {
				Arena a = _instance._arenaList.get(args[2]);
				try {
					Region selection = _instance.WE.getSession(player).getSelection(_instance.WE.getSession(player).getSelectionWorld());
					if(selection instanceof CuboidRegion) {
						CuboidRegion region = (CuboidRegion) selection;
						BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
						
						try(EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1)){
							ForwardExtentCopy forwardextendcopy = new ForwardExtentCopy(
									editSession, region, clipboard, region.getPos1()
									);
							Operations.complete(forwardextendcopy);
							File file = new File(_instance.getDataFolder().getAbsolutePath() + File.separator + a._Name + ".schem");
							file.createNewFile();
							try(ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(file))){
								writer.write(clipboard);
								a.world = editSession.getWorld().getName();
								
								a.x = region.getPos2().getX();
								a.y = region.getPos2().getY();
								a.z = region.getPos2().getZ();
							}
						}
					}
				}catch(WorldEditException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
