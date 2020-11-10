package fr.topeka.sheepwar.commands.arena;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.topeka.sheepwar.arena.Arena;
import org.bukkit.entity.Player;
import org.primesoft.asyncworldedit.api.playerManager.IPlayerEntry;
import org.primesoft.asyncworldedit.api.utils.IFuncParamEx;
import org.primesoft.asyncworldedit.api.worldedit.ICancelabeEditSession;
import org.primesoft.asyncworldedit.api.worldedit.IThreadSafeEditSession;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.extent.inventory.BlockBag;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

/**
 * 
 * @see https://worldedit.enginehub.org/en/latest/api/examples/clipboard/
 */
@CommandDeclaration(
		command = "SCHEMATIC",
		aliases = { "SCHEM" },
		usage = "/sw arena schem <arena_name>",
		permission = "sheepwar.arena.schem"
		)
public class CommandArenaSchem extends AbstractCommand {

	public CommandArenaSchem(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(_instance._arenaList.containsKey(args[2])) {
				Arena a = _instance._arenaList.get(args[2]);
				IPlayerEntry awePlayer = _instance.playerManager.getConsolePlayer();
				World world = BukkitAdapter.adapt(player.getWorld());
				int maxBlocks = -1;
				BlockBag bb = null;
				IThreadSafeEditSession es = _instance.esFactory.getThreadSafeEditSession(world, maxBlocks, bb, awePlayer);
				try {
					if(_instance.WE.getSession(player).isSelectionDefined(BukkitAdapter.adapt(player.getWorld()))) {
						Region selection = _instance.WE.getSession(player).getSelection(_instance.WE.getSession(player).getSelectionWorld());
						if(selection instanceof CuboidRegion) {
							CuboidRegion region = (CuboidRegion) selection;
							BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
							
							_instance.blockPlacer.performAsAsyncJob(es, awePlayer, "toSchem" + a._Name, new IFuncParamEx<Integer, ICancelabeEditSession, MaxChangedBlocksException>() {
								@Override
								public Integer execute(ICancelabeEditSession editSession) throws MaxChangedBlocksException{
									ForwardExtentCopy forwardextendcopy = new ForwardExtentCopy(
											editSession, region, clipboard, region.getMinimumPoint()
											);
									forwardextendcopy.setCopyingEntities(false);
									try {
										Operations.complete(forwardextendcopy);
										File file = new File(_instance.getDataFolder().getAbsolutePath() + File.separator + a._Name + ".schem");
										file.createNewFile();
										try(ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(file))){
											writer.write(clipboard);
											a.world = BukkitAdapter.adapt(editSession.getWorld());
											
											a.x = region.getMinimumPoint().getX();
											a.y = region.getMinimumPoint().getY();
											a.z = region.getMinimumPoint().getZ();
											player.sendMessage("Schematic saved");
											return 0;
										}
									} catch (WorldEditException | IOException e) {
										e.printStackTrace();
										player.sendMessage("Can't save schematic, please see console");
										return 1;
									}
								}
							});
							return true;
						}else {
							player.sendMessage("Your selection need to be cuboid");
							return false;
						}
					}
				}catch (IncompleteRegionException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	

}
