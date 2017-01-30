package com.momnop.yeoldechat;

import com.momnop.yeoldechat.config.ConfigHandler;
import com.momnop.yeoldechat.network.MessageSyncRoleplayMode;
import com.momnop.yeoldechat.network.PacketHandler;
import com.momnop.yeoldechat.player.PlayerRoleplayMode;
import com.momnop.yeoldechat.proxy.CommonProxy;
import com.momnop.yeoldechat.util.MessageChecker;
import com.momnop.yeoldechat.util.MessageDatabase;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

public class ModEventHandler {
	
	@SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            if (PlayerRoleplayMode.get((EntityPlayer) event.entity) == null)
            {
                PlayerRoleplayMode.register((EntityPlayer) event.entity);
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;

            PlayerRoleplayMode props = PlayerRoleplayMode.get(player);
            NBTTagCompound playerDataRoleplayMode = CommonProxy.getEntityData((player).getCommandSenderName());
            if (playerDataRoleplayMode != null)
            {
                props.loadNBTData(playerDataRoleplayMode);
            }
            
            PacketHandler.INSTANCE.sendTo(new MessageSyncRoleplayMode(player), (EntityPlayerMP) player);
        }
    }
	
	@SubscribeEvent
	public void chatReceived(ServerChatEvent event) {
		PlayerRoleplayMode props = PlayerRoleplayMode.get(event.player);
		if (props.getRoleplayMode() == true) {
			if (ConfigHandler.chatPrefixEnabled == true) {
				String sentMessage = "<" + event.player.getDisplayName() + ">" + " " + MessageDatabase.getBeginningWords() + MessageDatabase.getRandomPerson() + " " + MessageChecker.checkString(event.message);
			
				ChatComponentTranslation chat = new ChatComponentTranslation(sentMessage);
			
				event.component = chat;
			} else {
				String sentMessage = "<" + event.player.getDisplayName() + ">" + " " + MessageChecker.checkString(event.message);
				//MessageChecker.checkString(" " + event.message.charAt(0)).toUpperCase();
			
				ChatComponentTranslation chat = new ChatComponentTranslation(sentMessage);
			
				event.component = chat;
			}
		}
	}
	
}
