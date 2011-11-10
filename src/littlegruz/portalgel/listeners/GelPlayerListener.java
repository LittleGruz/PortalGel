package littlegruz.portalgel.listeners;

import littlegruz.portalgel.PortalGel;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class GelPlayerListener extends PlayerListener{

   public static PortalGel plugin;

   public GelPlayerListener(PortalGel instance){
      plugin = instance;
   }
   
   public void onPlayerMove(PlayerMoveEvent event){
      Player player = event.getPlayer();
      Location loc = player.getLocation();
      loc.setY(loc.getY() - 1);
      Block block = loc.getBlock();
      /*if(event.getPlayer().getFallDistance() > 1)
         event.getPlayer().sendMessage(Float.toString(event.getPlayer().getFallDistance()));*/
      if(plugin.getBlockMap().get(block.getLocation()) != null)
      {
         // Orange wool
         if(block.getType().compareTo(Material.WOOL) == 0
               && block.getData() == 1){
            Vector newVec = player.getLocation().getDirection().multiply(1.2);
            newVec.setY(newVec.getY()/1.1);
            player.setVelocity(newVec);
         }
         // Blue wool
         else if(block.getType().compareTo(Material.WOOL) == 0
               && block.getData() == 11){
            float fall;
            fall = event.getPlayer().getFallDistance();
            loc.setY(loc.getY() + 1);
   
            // Less than 1 block
            if(fall < 0.75);
            // 1 block
            else if(fall < 1.58)
               player.setVelocity(new Vector(0,0.53,0));
            // 2 blocks
            else if(fall < 2.68)
               player.setVelocity(new Vector(0,0.73,0));
            // 3 blocks
            else if(fall < 3.34)
               player.setVelocity(new Vector(0,0.8,0));
            // 4 blocks
            else if(fall < 4.84)
               player.setVelocity(new Vector(0,0.95,0));
            // 5 blocks
            else if(fall < 5.68)
               player.setVelocity(new Vector(0,1,0));
            // 6 blocks
            else if(fall < 6.59)
               player.setVelocity(new Vector(0,1.15,0));
            // 7 blocks
            else if(fall < 7.55)
               player.setVelocity(new Vector(0,1.25,0));
            // 8 blocks
            else if(fall < 8.58)
               player.setVelocity(new Vector(0,1.35,0));
            // 9 blocks
            else if(fall < 9.66)
               player.setVelocity(new Vector(0,1.45,0));
            // 10 blocks
            else if(fall < 10.80)
               player.setVelocity(new Vector(0,1.50,0));
            // 11 or more blocks; for whatever reason this doesn't work falling from 12 blocks
            else if(fall >= 10.80){
               player.setVelocity(new Vector(0,1.62,0));
            }
         }
      }
   }
}
