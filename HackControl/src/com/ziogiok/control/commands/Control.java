package com.ziogiok.control.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.ziogiok.control.Main;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Control implements CommandExecutor {
	
	private Main plugin;
	
	public Control(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
		 if(player.hasPermission("staff.staff")) {
		  if(args.length == 1) {
			   Player ptarget = Bukkit.getPlayerExact(args[0]);
			  if(plugin.cont.isEmpty()) {
			   if(ptarget != null) {
				   if(!(plugin.getConfig().contains("Point1") && plugin.getConfig().contains("Point2"))) {
					   player.sendMessage("§cPrima di eseguire un controllo devi settare i due punti");
					   player.sendMessage("§ccon§e /setzona 1§c e§e /setzona 2");
					   return true;
				   }
				   Location ploc = new Location(player.getWorld(), plugin.getConfig().getInt("Point1.x"), plugin.getConfig().getInt("Point1.y"), plugin.getConfig().getInt("Point1.z"));
				   Location tloc = new Location(player.getWorld(), plugin.getConfig().getInt("Point2.x"), plugin.getConfig().getInt("Point2.y"), plugin.getConfig().getInt("Point2.z"));
				   player.teleport(ploc);
				   ptarget.teleport(tloc);
				   plugin.opCont.add(player.getName());
				   plugin.cont.add(ptarget.getName());
				   IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§4§lControllo Hack\"}");
				   IChatBaseComponent chatSubTitle = ChatSerializer.a("{\"text\": \"§c§lSe lasci il gioco verrai bannato automaticamente\"}");
				   PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
				   PacketPlayOutTitle subTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle);
				   ((CraftPlayer) ptarget).getHandle().playerConnection.sendPacket(title);
				   ((CraftPlayer) ptarget).getHandle().playerConnection.sendPacket(subTitle);
				   player.sendMessage("§eTu§a e §e" + ptarget.getName() + "§a siete stati teletrasportati nella zona per il controllo hack!");
				   ptarget.sendMessage("§4Contrllo Hack!§c Segui le istruzioni che ti verranno fornite!");
			   } else {
				   player.sendMessage("§cIl giocatore '§e" + args[0] + "§c' non è al momento online!");
				   return true;
			   }
		     } else {
		    	 player.sendMessage("§cQualcun altro sta gia eseguendo un controllo hack!");
		    	 return true;
		     }
		   } else {
			   player.sendMessage("§cUso incorretto del comando.§e Per favore utilizza /control (nome player)");
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
