package com.ziogiok.control.events;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ziogiok.control.Main;

public class PlayerQuit implements Listener{
	
	private Main plugin;
	
	public PlayerQuit(Main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(plugin.cont.contains(e.getPlayer().getName())) {
			Bukkit.getBanList(Type.NAME).addBan(e.getPlayer().getName(), "§cDisconnesso durante controllo hack", null,"Console");		
			Bukkit.broadcastMessage("§eIl giocatore§c " + e.getPlayer().getName() + "§e è stato bannato per aver lasciato il gioco durante il controllo hack!");
			Bukkit.getPlayer(plugin.opCont.get(0)).teleport(e.getPlayer().getWorld().getSpawnLocation());
			Bukkit.getPlayer(plugin.opCont.get(0)).sendMessage("§aIl giocatore si è disconnesso sei stato riportato allo spawn.");
			plugin.cont.clear();
			plugin.opCont.clear();			
		}
	}

}
