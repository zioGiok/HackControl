package com.ziogiok.control.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ziogiok.control.Main;

public class Setzona implements CommandExecutor {
	
	private Main plugin;
	
	public Setzona(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			 if(player.hasPermission("staff.staff")) {
			  if(args.length == 0) {
				  player.sendMessage("§cUso incorretto del comando.§e Per favore utilizza /setzona 1:2");
				   return true;
			  }
			   if(args[0].equals("1") && args.length == 1 || args[0].equals("2") && args.length == 1) {
				if(args[0].equals("1")) {
					Location loc = player.getLocation();
					plugin.getConfig().set("Point1.x", loc.getBlockX());
					plugin.getConfig().set("Point1.y", loc.getBlockY());
					plugin.getConfig().set("Point1.z", loc.getBlockZ());
					plugin.saveConfig();
					player.sendMessage("§aPunto 1 della zona controllo hack successivamente settato!");
				  }
				if(args[0].equals("2")) {
					Location loc = player.getLocation();
					plugin.getConfig().set("Point2.x", loc.getBlockX());
					plugin.getConfig().set("Point2.y", loc.getBlockY());
					plugin.getConfig().set("Point2.z", loc.getBlockZ());
					plugin.saveConfig();
					player.sendMessage("§aPunto 2 della zona controllo hack successivamente settato!");
				  }
			  } else {
				   player.sendMessage("§cUso incorretto del comando.§e Per favore utilizza /setzona 1:2");
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
