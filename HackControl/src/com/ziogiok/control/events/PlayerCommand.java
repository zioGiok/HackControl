package com.ziogiok.control.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.ziogiok.control.Main;

public class PlayerCommand implements Listener {
	
	private Main plugin;
	
	public PlayerCommand(Main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		if(plugin.cont.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cNon puoi eseguire comandi mentre sei sotto controllo hack!");
		}
	}
}
