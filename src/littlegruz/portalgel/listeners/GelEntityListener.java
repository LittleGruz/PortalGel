package littlegruz.portalgel.listeners;

import littlegruz.portalgel.PortalGel;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GelEntityListener implements Listener {

	private PortalGel plugin;

	public GelEntityListener(PortalGel instance) {
		plugin = instance;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player playa = (Player) event.getEntity();

			if (playa.getFallDistance() > 0
					&& plugin.getConfig().getStringList("worlds").contains(playa.getWorld().getName())
					&& playa.getInventory().getBoots() != null
					&& playa.getInventory().getBoots().getType().compareTo(Material.IRON_BOOTS) == 0) {
				event.setCancelled(true);
			}
		}
	}

}
