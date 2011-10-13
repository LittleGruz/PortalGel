package littlegruz.portalgoo;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class GooEntityListener extends EntityListener{
   public static PortalGoo plugin;

   public GooEntityListener(PortalGoo instance){
      plugin = instance;
   }
   
   public void onEntityDamage(EntityDamageEvent event){
      if(event.getEntity() instanceof Player){
         Player playa = (Player) event.getEntity();
         Location loc = playa.getLocation();
         loc.setY(loc.getY() - 1);
         Block block = loc.getBlock();
         
         if(playa.getFallDistance() > 0 && block.getType().compareTo(Material.WOOL) == 0 && block.getData() == 11){
            event.setCancelled(true);
         }
      }
   }
}
