package com.momnop.yeoldechat.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler
{
    public static Configuration configuration;
    public static boolean chatPrefixEnabled = true;
    
    public static void init(File file)
    {
        if (configuration == null)
        {
            configuration = new Configuration(file);
            loadConfiguration();
        }
    }
    
    public static void loadConfiguration()
    {
        chatPrefixEnabled = configuration.getBoolean(ConfigInfo.CHAT_PREFIX_ENABLED_NAME, Configuration.CATEGORY_GENERAL, chatPrefixEnabled, ConfigInfo.CHAT_PREFIX_ENABLED_DESC);
        
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
    
    public static String configToString(boolean bool) {
    	if (bool == true) {
    		return "true";
    	}
    	return "false";
    }
}