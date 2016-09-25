package com.ziogiok.control.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ziogiok.control.Main;

public class Cfinish implements CommandExecutor {
	
	private Main plugin;
	
	public Cfinish(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
		 if(player.hasPermission("staff.staff")) {
		  if(args.length == 0) {
			 if(!plugin.cont.isEmpty()) {
				if(plugin.opCont.contains(player.getName())) {
				   Player ptarget = Bukkit.getPlayer(plugin.cont.get(0));
				   player.teleport(player.getWorld().getSpawnLocation());
				   ptarget.teleport(ptarget.getWorld().getSpawnLocation());
				   plugin.cont.clear();
				   plugin.opCont.clear();
				   player.sendMessage("§aControllo hack terminato!");
				   ptarget.sendMessage("§aControllo hack terminato!");
				} else {
					player.sendMessage("§cNon puoi interrompere il controllo hack se non lo stai eseguendo tu!");
				}
			   } else {
				   player.sendMessage("§cNon si stanno eseguendo controlli hack al momento!");
				   return true;
			    }
		   } else {
			   player.sendMessage("§cUso incorretto del comando.§e Per favore utilizza /cfinish");
			   return true;
		   }
		  } else {
			  player.sendMessage("§cPermessi insufficienti per eseguire questo comando!");
		  }
		} else {
			sender.sendMessage("§cDevi essere un player per eseguire questo comando!");
		}
		return false;
	}
}
