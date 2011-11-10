package littlegruz.portalgel;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class GelEntityListener extends EntityListener{
   public static PortalGel plugin;

   public GelEntityListener(PortalGel instance){
      plugin = instance;
   }
   
   public void onEntityDamage(EntityDamageEvent event){
      if(event.getEntity() instanceof Player){
         Player playa = (Player) event.getEntity();
         /*Location loc = playa.getLocation();
         loc.setY(loc.getY() - 1);
         Block block = loc.getBlock();*/
         
         if(playa.getFallDistance() > 0 && plugin.isBootsActive() && playa.getInventory().getBoots().getType().compareTo(Material.IRON_BOOTS) == 0){
            event.setCancelled(true);
         }
      }
   }
}
