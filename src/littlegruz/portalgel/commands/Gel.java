package littlegruz.portalgel.commands;

import littlegruz.portalgel.PortalGel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Gel implements CommandExecutor{
   private PortalGel plugin;
   
   public Gel(PortalGel instance){
      plugin = instance;
   }

   @Override
   public boolean onCommand(CommandSender sender, Command cmd,
         String commandLabel, String[] args){
      if(commandLabel.compareToIgnoreCase("gelplacement") == 0){
         if(sender.hasPermission("portalgel.gelplacement")){
            if(args.length == 1){
               if(args[0].compareToIgnoreCase("on") == 0){
                  plugin.setPlacing(true);
                  sender.sendMessage("Gel placement is turned on");
               }
               else if(args[0].compareToIgnoreCase("off") == 0){
                  plugin.setPlacing(false);
                  sender.sendMessage("Gel placement is turned off");
               }
            }
            else
               sender.sendMessage("Correct format for command is: /gelplacement <on|off>");
         }
         else
            sender.sendMessage("You don't have sufficient permissions");
      }
      else
         return false;
      return true;
   }

}
