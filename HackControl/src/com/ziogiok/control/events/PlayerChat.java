package com.ziogiok.control.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.ziogiok.control.Main;

public class PlayerChat implements Listener {
	
	private Main plugin;
	
	public PlayerChat(Main pl) {
		plugin = pl;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
	 if(!plugin.cont.isEmpty()) {
      if(plugin.cont.contains(e.getPlayer().getName()) || plugin.opCont.contains(e.getPlayer().getName())) {
	   for(Player player : Bukkit.getOnlinePlayers()){
		 if(!(plugin.cont.contains(player.getName()) || plugin.opCont.contains(player.getName()))) {
			 e.getRecipients().remove(player);
		 } 
	    }
	   } else {
		   for(Player player : Bukkit.getOnlinePlayers()){
			   if(plugin.cont.contains(player.getName()) || plugin.opCont.contains(player.getName())) {
				   e.getRecipients().remove(player);
			   }
		   }
	   }
	 }
   }
}
