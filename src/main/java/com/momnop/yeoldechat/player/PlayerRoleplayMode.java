package com.momnop.yeoldechat.player;

import com.momnop.yeoldechat.network.MessageSyncRoleplayMode;
import com.momnop.yeoldechat.network.PacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerRoleplayMode implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "yeoldechat" + "_RoleplayMode";

    private boolean roleplayMode;

    private EntityPlayer player;

    public PlayerRoleplayMode(EntityPlayer player)
    {
    	this.roleplayMode = false;
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(PlayerRoleplayMode.EXT_PROP_NAME, new PlayerRoleplayMode(player));
    }

    /**
     * Returns the players currency instance.
     * @param player
     *        The player to get the currency instance from.
     */
    public static final PlayerRoleplayMode get(EntityPlayer player)
    {
        return (PlayerRoleplayMode) player.getExtendedProperties(EXT_PROP_NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();

        properties.setBoolean("roleplayMode", roleplayMode);;

        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
        roleplayMode = properties.getBoolean("roleplayMode");
    }

    @Override
    public void init(Entity entity, World world)
    {
    }

    public void sendPacket()
    {
        if (player.worldObj.isRemote == false)
    	PacketHandler.INSTANCE.sendTo(new MessageSyncRoleplayMode(player), (EntityPlayerMP) player);
    }
    
    /**
     * Returns if the player has a nickname yet.
     */
    public boolean getRoleplayMode()
    {
    	return roleplayMode;
    }
    
    public void setRoleplayMode(boolean i, boolean packet)
    {
    	this.roleplayMode = i;
    	
        if (packet)
        {
            sendPacket();
        }
    }
}