package com.momnop.yeoldechat.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("yeoldechat");

    public static int counter = 0;

    public static void init()
    {
        registerMessage(MessageSyncRoleplayMode.class, MessageSyncRoleplayMode.class, Side.CLIENT);
    }

    public static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side receivingSide)
    {
        INSTANCE.registerMessage(messageHandler, requestMessageType, counter, receivingSide);
        counter++;
    }
}