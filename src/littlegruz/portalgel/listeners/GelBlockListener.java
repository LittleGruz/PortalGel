package littlegruz.portalgel.listeners;

import littlegruz.portalgel.PortalGel;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class GelBlockListener implements Listener{
   private PortalGel plugin;
   
   public GelBlockListener(PortalGel instance){
      plugin = instance;
   }
   
   @EventHandler
   public void onBlockPlace(BlockPlaceEvent event){
      if(event.getBlock().getType().compareTo(Material.WOOL) == 0 && plugin.isPlacing()){
         if(event.getBlock().getData() == 11){
            plugin.getBlockMap().put(event.getBlock().getLocation(), "BLUE");
         }
         else if(event.getBlock().getData() == 1){
            plugin.getBlockMap().put(event.getBlock().getLocation(), "BLACK");
         }
      }
   }

   @EventHandler
   public void onBlockBreak(BlockBreakEvent event){
      if(plugin.getBlockMap().get(event.getBlock().getLocation()) != null){
         plugin.getBlockMap().remove(event.getBlock().getLocation());
      }
   }
}
