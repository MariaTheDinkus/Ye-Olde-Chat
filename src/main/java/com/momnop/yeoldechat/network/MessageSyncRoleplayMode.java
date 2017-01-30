package com.momnop.yeoldechat.network;

import java.io.IOException;

import com.momnop.yeoldechat.YeOldeChat;
import com.momnop.yeoldechat.player.PlayerRoleplayMode;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageSyncRoleplayMode implements IMessage, IMessageHandler<MessageSyncRoleplayMode, IMessage>
{
	private boolean roleplayMode;

    public MessageSyncRoleplayMode()
    {
    }

    public MessageSyncRoleplayMode(EntityPlayer p)
    {
        PlayerRoleplayMode props = PlayerRoleplayMode.get(p);
        roleplayMode = props.getRoleplayMode();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
    	roleplayMode = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
    	buf.writeBoolean(roleplayMode);
    }

    @Override
    public IMessage onMessage(MessageSyncRoleplayMode message, MessageContext ctx)
    {
        EntityPlayer player = YeOldeChat.proxy.getPlayerFromMessageContext(ctx);
        if (player == null)
            return null;
        
        PlayerRoleplayMode props = PlayerRoleplayMode.get(player);
        if (props == null) {
        	return null;
        }
        
        if (player.worldObj.isRemote)
        props.setRoleplayMode(message.roleplayMode, true);
        
        return null;
    }
}