package littlegruz.portalgoo;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PortalGoo extends JavaPlugin{
   Logger log = Logger.getLogger("This is MINECRAFT!");
   private final GooPlayerListener playerListener = new GooPlayerListener(this);
   private final GooEntityListener entityListener = new GooEntityListener(this);

   public void onEnable(){
      log.info("Portal Goo v1.1.1 enabled");
      PluginManager pm = this.getServer().getPluginManager();
      pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
      pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
   }

   public void onDisable(){
      log.info("Portal Goo v1.1.1 disabled");
   }

}
