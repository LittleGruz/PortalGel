package littlegruz.portalgel;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PortalGel extends JavaPlugin{
   Logger log = Logger.getLogger("This is MINECRAFT!");
   private final GelPlayerListener playerListener = new GelPlayerListener(this);
   private final GelEntityListener entityListener = new GelEntityListener(this);
   private boolean bootsActive;

   public void onEnable(){
      PluginManager pm = this.getServer().getPluginManager();
      pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
      pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
      bootsActive = false;
      log.info("Portal Gel v1.2 enabled");
   }

   public void onDisable(){
      log.info("Portal Gel v1.2 disabled");
   }

   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
      if(commandLabel.compareToIgnoreCase("portalboots") == 0){
         if(args.length == 1){
            if(args[0].compareToIgnoreCase("on") == 0){
               bootsActive = true;
               sender.sendMessage("Portal boots turned on");
            }
            else if(args[0].compareToIgnoreCase("off") == 0){
               bootsActive = false;
               sender.sendMessage("Portal boots turned off");
            }
         }
         else
            sender.sendMessage("Correct format for command is: /portalboots <on|off>");
         return true;
      }
      return false;
   }

   public boolean isBootsActive(){
      return bootsActive;
   }
}
