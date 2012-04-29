package littlegruz.portalgel;

import java.io.File;

import littlegruz.portalgel.listeners.GelEntityListener;
import littlegruz.portalgel.listeners.GelPlayerListener;

import org.bukkit.plugin.java.JavaPlugin;

public class PortalGel extends JavaPlugin {

	public void onEnable() {
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		reloadConfig();

		getServer().getPluginManager().registerEvents(new GelEntityListener(this), this);
		getServer().getPluginManager().registerEvents(new GelPlayerListener(this), this);
	}

}
