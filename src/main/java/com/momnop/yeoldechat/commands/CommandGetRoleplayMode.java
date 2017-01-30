package com.momnop.yeoldechat.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.momnop.yeoldechat.player.PlayerRoleplayMode;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandGetRoleplayMode extends CommandBase {
	private List aliases;

	public CommandGetRoleplayMode() {
		this.aliases = new ArrayList();
		this.aliases.add("getrpmode");
		this.aliases.add("getrp");
	}

	@Override
	public String getCommandName() {
		return "getroleplaymode";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender) {
		String[] str = { "/getroleplaymode" };
		List l = new ArrayList();
		for (int i = 0; i < str.length; i++) {
			l.add(EnumChatFormatting.RED);
			l.add(str[i]);
		}
		StringBuilder finalStr = new StringBuilder();
		for (int i = 0; i < l.size(); i++) {
			finalStr.append(l.get(i));
		}
		return finalStr.toString();
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
		return super.canCommandSenderUseCommand(par1ICommandSender);
	}

	@Override
	public void processCommand(ICommandSender par1ICommandSender,
			String[] par2ArrayOfStr) {
		EntityPlayer player = (EntityPlayer) par1ICommandSender;
		PlayerRoleplayMode props = PlayerRoleplayMode.get(player);

		player.addChatComponentMessage(new ChatComponentText("Your roleplay mode is currently: " + props.getRoleplayMode()));
	}

	public void sendWrongUsageMessage(EntityPlayer p) {
		p.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED
				+ "Proper " + EnumChatFormatting.RED + "usage: "
				+ getCommandUsage(p)));
	}

	public void sendInvalidPlayerMessage(EntityPlayer p, String invalidPlayer) {
		p.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED
				+ "Player " + EnumChatFormatting.YELLOW + invalidPlayer
				+ EnumChatFormatting.RED + " could not be found!"));
	}

	public void sendInvalidValueMessage(EntityPlayer p, String invalidPlayer) {
		p.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED
				+ "Nickname " + EnumChatFormatting.YELLOW + invalidPlayer
				+ EnumChatFormatting.RED + " is invalid!"));
	}

	public void sendMissingPropertiesMessage(EntityPlayer p) {
		p.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED
				+ "Properties for player " + EnumChatFormatting.YELLOW
				+ p.getCommandSenderName() + EnumChatFormatting.RED
				+ " could not be found!"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender,
			String[] par2ArrayOfStr) {
		List l = new ArrayList();
		List userList = getListOfStringsMatchingLastWord(par2ArrayOfStr,
				MinecraftServer.getServer().getAllUsernames());
		return par2ArrayOfStr.length >= 1 ? l : null;
	}
}