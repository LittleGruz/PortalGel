package littlegruz.portalgel.commands;

import littlegruz.portalgel.PortalGel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Apparel implements CommandExecutor{
   private PortalGel plugin;
   
   public Apparel(PortalGel instance){
      plugin = instance;
   }

   @Override
   public boolean onCommand(CommandSender sender, Command cmd,
         String commandLabel, String[] args){
      if(commandLabel.compareToIgnoreCase("portalboots") == 0){
         if(sender.hasPermission("portalgel.portalboots")){
            if(args.length == 1){
               if(args[0].compareToIgnoreCase("on") == 0){
                  plugin.setBoots(true);
                  sender.sendMessage("Portal boots turned on");
               }
               else if(args[0].compareToIgnoreCase("off") == 0){
                  plugin.setBoots(false);
                  sender.sendMessage("Portal boots turned off");
               }
            }
            else
               sender.sendMessage("Correct format for command is: /portalboots <on|off>");

         }else
            sender.sendMessage("You don't have sufficient permissions");
      }
      else
         return false;
      return true;
   }

}
