package com.arsenicclient;

import com.arsenicclient.command.client.ChatManager;
import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.font.impl.FontUtil;
import com.arsenicclient.hud.notifications.NotificationManager;
import com.arsenicclient.module.ModuleManager;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Arsenic {
	
    private static Arsenic INSTANCE = new Arsenic();

    private ModuleManager moduleManager;
    
    private NotificationManager notificationManager;
    
    private EventBus shuttle;
    
    private ChatManager chatManager;
    
    private String prefix = ".";

    public void init() {
    	
        FontUtil.bootstrap();

        shuttle = new EventBus();
        
        this.moduleManager = new ModuleManager();
        
        notificationManager = new NotificationManager();
        
        moduleManager.init();
        
        chatManager = new ChatManager();
        
    }
    
    public void addMessage(final Object msg) {
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§e [ARSENIC] §r " + msg));
		
	}
    
    public static Arsenic getArsenic() {
    	
    	return INSTANCE;
    	
    }
    
    public ModuleManager getModuleManager() {
    	
    	return this.moduleManager;
    	
    }
    
    public NotificationManager getNotificationManager() {
    	
    	return this.notificationManager;
    	
    }
    
    public EventBus getShuttle() {
    	return this.shuttle;
    }
    
    public String getPrefix() {
    	return this.prefix;
    }
    
    public void setPrefix(final String prefix) {
    	this.prefix = prefix;
    }

}
