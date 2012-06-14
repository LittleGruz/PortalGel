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

import littlegruz.portalgel.commands.Apparel;
import littlegruz.portalgel.commands.Gel;
import littlegruz.portalgel.listeners.GelBlockListener;
import littlegruz.portalgel.listeners.GelEntityListener;
import littlegruz.portalgel.listeners.GelPlayerListener;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class PortalGel extends JavaPlugin{
   private Logger log = Logger.getLogger("This is MINECRAFT!");
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
         String world;
         
         while((input = br.readLine()) != null){
            st = new StringTokenizer(input, " ");
            
            /* This stuff is here so users do not need to worry about converting
             * from world UIDs to world names */
            world = st.nextToken();
            try{
            if(getServer().getWorld(UUID.fromString(world)) != null)
               world = getServer().getWorld(UUID.fromString(world)).getName();
            }catch(IllegalArgumentException e){}
            
            blockMap.put(new Location(getServer().getWorld(world), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())), st.nextToken());
         }
         br.close();
         
      }catch(FileNotFoundException e){
         log.info("No original Portal file, creating new one.");
      }catch(IOException e){
         log.info("Error reading Portal file");
      }catch(Exception e){
         log.info("Incorrectly formatted Portal file");
      }

      getServer().getPluginManager().registerEvents(new GelBlockListener(this), this);
      getServer().getPluginManager().registerEvents(new GelEntityListener(this), this);
      getServer().getPluginManager().registerEvents(new GelPlayerListener(this), this);

      getCommand("gelplacement").setExecutor(new Gel(this));
      getCommand("portalboots").setExecutor(new Apparel(this));
      
      bootsActive = false;
      placing = false;
      
      log.info(this.toString() + " enabled");
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
            bw.write(block.getKey().getWorld().getName() + " "
                  + Double.toString(block.getKey().getX()) + " "
                  + Double.toString(block.getKey().getY()) + " "
                  + Double.toString(block.getKey().getZ()) + " "
                  + block.getValue() + "\n");
         }
         bw.close();
      }catch(IOException e){
         log.info("Error saving Portal blocks");
      }
      log.info(this.toString() + " disabled");
   }

   public boolean isBootsActive(){
      return bootsActive;
   }

   public void setBoots(boolean boots){
      bootsActive = boots;
   }

   public boolean isPlacing(){
      return placing;
   }

   public void setPlacing(boolean placing){
      this.placing = placing;
   }

   public HashMap<Location, String> getBlockMap(){
      return blockMap;
   }
}
