package com.arsenicclient.module.impl.movement;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.Arsenic;
import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.hud.notifications.Notification.Type;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.arsenicclient.settings.impl.ModeSetting;
import com.arsenicclient.settings.impl.NumberSetting;
import com.arsenicclient.utils.impl.MoveUtil;
import com.google.common.eventbus.Subscribe;

import net.minecraft.potion.Potion;
import com.arsenicclient.hud.notifications.Notification.Type;

@ModuleInfo(name = "Speed", description = "Modifes movement", category = Category.MOVEMENT)
public class Speed extends Module {

	private double y;
	
	ModeSetting mode = new ModeSetting("Mode", "Watchdog", "Vanilla", "Watchdog", "Watchdog2");
	NumberSetting speed = new NumberSetting("Speed", 1, 0.1f, 2, 0.01f);
	
	public Speed() {
		super();
		this.registerSettings(mode, speed);
	}
	
	public void onEnable() {
		y = mc.thePlayer.posY;
		Arsenic.getArsenic().getNotificationManager().postNotification("you are Using Speed!", 2000, Type.ENABLE);
	}
	
	@Subscribe
	public void onPreMotion(EventPreMotion event) {
		if(!this.isEnabled()) return;
		float fast = speed.getValue();
		
		if (mc.thePlayer.onGround) {
			y = mc.thePlayer.posY;

			mc.thePlayer.jump();
		}

		switch(mode.getMode()) {
		case "Vanilla":
			MoveUtil.strafe(fast);
			break;
		case "Watchdog":
			float m = mc.thePlayer.isPotionActive(Potion.moveSpeed) ? (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1) : 0;
			
			if(mc.thePlayer.onGround)
				MoveUtil.strafe(0.482 + m);
			else {
				if(mc.thePlayer.motionY < 0.15 && mc.thePlayer.motionY > 0)
					mc.thePlayer.motionY = 0;
			}
			
			MoveUtil.strafe(MoveUtil.getBaseSpeed() * 1.001);
			break;
		}
		
	}
	
}
