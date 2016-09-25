package com.ziogiok.control;

import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.ziogiok.control.commands.Cfinish;
import com.ziogiok.control.commands.Control;
import com.ziogiok.control.commands.Setzona;
import com.ziogiok.control.events.PlayerChat;
import com.ziogiok.control.events.PlayerCommand;
import com.ziogiok.control.events.PlayerQuit;

public class Main extends JavaPlugin implements Listener {
    
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getConsoleSender().sendMessage(getDescription().getName() + " v" + getDescription().getVersion() + " è stato abilitato!");				
		
		regCommands();
		regEvents();
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	public void regCommands() {
		getCommand("control").setExecutor(new Control(this));
		getCommand("setzona").setExecutor(new Setzona(this));
		getCommand("cfinish").setExecutor(new Cfinish(this));
	}
	public void regEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerQuit(this), this);
		pm.registerEvents(new PlayerCommand(this), this);
		pm.registerEvents(new PlayerChat(this), this);
	}
	public ArrayList<String> cont = new ArrayList<String>();
	public ArrayList<String> opCont = new ArrayList<String>();
}