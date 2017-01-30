package com.momnop.yeoldechat;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Level;

import com.momnop.yeoldechat.commands.CommandSetRoleplayMode;
import com.momnop.yeoldechat.config.ConfigHandler;
import com.momnop.yeoldechat.network.PacketHandler;
import com.momnop.yeoldechat.proxy.CommonProxy;
import com.momnop.yeoldechat.util.MessageDatabase;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(name = "Ye Olde Chat", modid = "yeoldechat", version = "1.0.0", acceptableRemoteVersions = "*")
public class YeOldeChat
{
	@SidedProxy(clientSide = "com.momnop.yeoldechat.proxy.ClientProxy", serverSide = "com.momnop.yeoldechat.proxy.CommonProxy")
    public static CommonProxy proxy;
	
    public static YeOldeChat INSTANCE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ConfigHandler.init(event.getSuggestedConfigurationFile());
    	PacketHandler.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(new ModEventHandler());
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSetRoleplayMode());
    }
}