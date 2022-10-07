package com.arsenicclient.hud.notifications;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.arsenicclient.Arsenic;
import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.event.impl.render.EventRender2D;
import com.arsenicclient.font.impl.FontUtil;
import com.arsenicclient.hud.notifications.Notification.Type;
import com.arsenicclient.hud.renderer.HudRenderer;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class NotificationManager {

	private final List<Notification> notifications = new ArrayList<Notification>();
	
	public NotificationManager() {
		Arsenic.getArsenic().getShuttle().register(this);
	}

	@Subscribe
	public void onRender2D(EventRender2D event) {
		int count = 0;
		int color = new Color(0, 0, 0, 100).hashCode();
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		for (Notification n : notifications) {
			double imgSize = 20;
			double width = imgSize + FontUtil.normal.getStringWidth(n.getInfo()) + 2;
			double height = imgSize + 3;
			double offset = 5;

			if (n.getClock().getTime() <= n.getTime())
				n.x = sr.getScaledWidth() - width - offset;
			else {
				n.x += (sr.getScaledWidth() - n.x) / 30 + 0.05;
				if (n.x > sr.getScaledWidth())
					notifications.remove(n);
			}
			Gui.rect(n.x, sr.getScaledHeight() - offset - height - 10 - count * (height + offset), width, height,
					color);
			
			Gui.rect(n.x, sr.getScaledHeight() - offset - height - 12 - count * (height + offset) + height, (n.getClock().getTime() / n.getTime()) * width, 2,
					HudRenderer.getColor().hashCode());
			
			FontUtil.normal.drawStringWithShadow(n.getInfo(), n.x + imgSize,
					(float) (sr.getScaledHeight() - offset - height - 3 - count * (height + offset)), -1);
			
			FontUtil.icons.drawCenteredStringWithShadow("a", (float) (n.x + imgSize / 2),
					(float) (sr.getScaledHeight() - offset - height - 9 - count * (height + offset)),
					HudRenderer.getHUD().getColor().hashCode());
			count++;
		}
	}
	
	public void postNotification(String info, long time, Type type) {
		this.notifications.add(new Notification(info, time, type));
	}

}
