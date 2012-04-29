package littlegruz.portalgel.listeners;

import littlegruz.portalgel.PortalGel;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
//import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class GelPlayerListener implements Listener {

	public PortalGel plugin;

	public GelPlayerListener(PortalGel instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		loc.setY(loc.getY() - 1);
		Block block = loc.getBlock();

		if (plugin.getConfig().getStringList("worlds").contains(player.getWorld().getName())) {
			// Orange wool
			if (block.getType().compareTo(Material.WOOL) == 0
					&& block.getData() == 1) {
				Vector newVec = player.getLocation().getDirection().multiply(1.2);
				newVec.setY(0);
				player.setVelocity(newVec);
			}
			// Blue wool
			else if (block.getType().compareTo(Material.WOOL) == 0
					&& block.getData() == 11) {
				player.setVelocity(new Vector(0, 1, 0));
			}
		}
	}

}
