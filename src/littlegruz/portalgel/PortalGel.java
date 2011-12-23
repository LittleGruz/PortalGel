package littlegruz.portalgel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.logging.Logger;

import littlegruz.portalgel.listeners.GelBlockListener;
import littlegruz.portalgel.listeners.GelEntityListener;
import littlegruz.portalgel.listeners.GelPlayerListener;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PortalGel extends JavaPlugin{
   Logger log = Logger.getLogger("This is MINECRAFT!");
   private final GelPlayerListener playerListener = new GelPlayerListener(this);
   private final GelEntityListener entityListener = new GelEntityListener(this);
   private final GelBlockListener blockListener = new GelBlockListener(this);
   private boolean bootsActive;
   private boolean placing;
   private File blockFile;
   private HashMap<Location, String> blockMap;

   public void onEnable(){
      //Create the directory and files if needed
      new File(getDataFolder().toString()).mkdir();
      blockFile = new File(getDataFolder().toString() + "/portalBlocks.txt");
      
      //Load the file data
      blockMap = new HashMap<Location, String>();
      try{
         BufferedReader br = new BufferedReader(new FileReader(blockFile));
         StringTokenizer st;
         String input;
         while((input = br.readLine()) != null){
            st = new StringTokenizer(input, " ");
            blockMap.put(new Location(getServer().getWorld(UUID.fromString(st.nextToken())), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())), st.nextToken());
         }
         br.close();
         
      }catch(FileNotFoundException e){
         log.info("No original Portal file, creating new one.");
      }catch(IOException e){
         log.info("Error reading Portal file");
      }catch(Exception e){
         log.info("Incorrectly formatted Portal file");
      }
      
      PluginManager pm = this.getServer().getPluginManager();
      pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
     // pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
      pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
      pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
      pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
      bootsActive = false;
      placing = false;
      log.info("Portal Gel v1.3.1 enabled");
   }

   public void onDisable(){
      BufferedWriter bw;
      log.info("Saving Portal block data...");
      try{
         bw = new BufferedWriter(new FileWriter(blockFile));
         
         // Save all Portal blocks to file
         Iterator<Map.Entry<Location, String>> it = blockMap.entrySet().iterator();
         while(it.hasNext()){
            Entry<Location, String> block = it.next();
            bw.write(block.getKey().getWorld().getUID().toString() + " "
                  + Double.toString(block.getKey().getX()) + " "
                  + Double.toString(block.getKey().getY()) + " "
                  + Double.toString(block.getKey().getZ()) + " "
                  + block.getValue() + "\n");
         }
         bw.close();
      }catch(IOException e){
         log.info("Error saving Portal blocks");
      }
      log.info("Portal Gel v1.3.1 disabled");
   }

   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
      if(commandLabel.compareToIgnoreCase("portalboots") == 0){
         if(sender.hasPermission("portalgel.portalboots")){
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

         }else
            sender.sendMessage("You don't have sufficient permissions");
         return true;
      }
      else if(commandLabel.compareToIgnoreCase("gelplacement") == 0){
         if(sender.hasPermission("portalgel.gelplacement")){
            if(args.length == 1){
               if(args[0].compareToIgnoreCase("on") == 0){
                  placing = true;
                  sender.sendMessage("Gel placement is turned on");
               }
               else if(args[0].compareToIgnoreCase("off") == 0){
                  placing = false;
                  sender.sendMessage("Gel placement is turned off");
               }
            }
            else
               sender.sendMessage("Correct format for command is: /gelplacement <on|off>");
         }
         else
            sender.sendMessage("You don't have sufficient permissions");
         return true;
      }
      return false;
   }

   public boolean isBootsActive(){
      return bootsActive;
   }

   public boolean isPlacing(){
      return placing;
   }

   public HashMap<Location, String> getBlockMap(){
      return blockMap;
   }
}
