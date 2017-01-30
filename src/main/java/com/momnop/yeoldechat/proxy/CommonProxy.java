package com.momnop.yeoldechat.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy
{
    public static final Map<String, NBTTagCompound> dataRoleplayMode = new HashMap<String, NBTTagCompound>();

    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        dataRoleplayMode.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        return dataRoleplayMode.remove(name);
    }
    
    public EntityPlayer getPlayerEntity( MessageContext ctx )
	{
		return ctx.getServerHandler().playerEntity;
	}

    public EntityPlayer getPlayerFromMessageContext(MessageContext ctx)
    {
        switch (ctx.side)
        {
            case SERVER:
            {
                EntityPlayer entityPlayerMP = ctx.getServerHandler().playerEntity;
                return entityPlayerMP;
            }
            default:
            {
                break;
            }
        }
        return null;
    }
}