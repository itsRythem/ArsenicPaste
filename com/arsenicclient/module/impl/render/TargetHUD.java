package com.arsenicclient.module.impl.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.Arsenic;
import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.event.impl.render.EventRender2D;
import com.arsenicclient.font.impl.FontUtil;
import com.arsenicclient.hud.Dropdown;
import com.arsenicclient.hud.renderer.HudRenderer;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.arsenicclient.module.impl.combat.Aura;
import com.arsenicclient.settings.impl.BooleanSetting;
import com.arsenicclient.settings.impl.ModeSetting;
import com.arsenicclient.settings.impl.NumberSetting;
import com.arsenicclient.utils.impl.RenderUtil;
import com.arsenicclient.utils.impl.system.Clock;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;

@ModuleInfo(name = "Target HUD", description = "Shows target info", category = Category.RENDER)
public class TargetHUD extends Module {

	private float sHealth;
	public float l, length, height, scale;
	private EntityLivingBase entity;
	private Clock clock = new Clock();
	
	public ModeSetting mode = new ModeSetting("Mode", "Default", "Default", "Novoline", "Astolfo", "Mint");
	
	public TargetHUD() {
		this.registerSettings(mode);
	}

	public void onEnable() {
	}
	
	@Subscribe
	public void onRender2D(EventRender2D event) {
		this.setSuffix(mode.getMode());
		Aura aura = (Aura) Arsenic.getArsenic().getModuleManager().getModule("Aura");
		
		ScaledResolution sr = new ScaledResolution(mc);
		if(clock.getTime() > 100 / 60) {
		if(aura.targets != null && !aura.targets.isEmpty() && aura.isEnabled() || mc.currentScreen instanceof GuiChat) {
			entity = mc.currentScreen instanceof GuiChat ? mc.thePlayer : (EntityLivingBase) aura.targets.get(0);
			if(scale < 1) scale += ((1 - scale) / 8) + 0.005;
			else scale = 1;;
		}
		else if(scale <= 0) {
		scale = 0;
		entity = null;
		} else scale -= ((scale) / 8) + 0.005;

		posX = sr.getScaledWidth() / 2;
		posY = sr.getScaledHeight() / 1.8;
			 
		if(entity != null)
			drawTargetHUD((EntityLivingBase) entity, posX, posY);
		
		clock.reset();
	}
	}
	
	public void drawTargetHUD(EntityLivingBase entity, double x, double y) {
		FontRenderer fr = mc.fontRendererObj;
		ScaledResolution sr = new ScaledResolution(mc);
	
		int hudColor = HudRenderer.getColor().hashCode();
		double health = ((double)Math.round(entity.getHealth() * 10)) / 10;
		float dist = (float)Math.round(mc.thePlayer.getDistanceToEntity(entity) * 10) / 10;
		double smoothSpeed = 10;
		
		if(sHealth > 20) sHealth = 20;
			
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + ((1 - scale) * length / 2), y + ((1 - scale) * height / 2), 0);
		GlStateManager.scale(scale, scale, 1);
		
		switch(mode.getMode()) {
		case "Astolfo":
			length = 140;
			height = 45;
			Gui.rect(0, 0, 140, 45, new Color(0, 0, 0, 110).hashCode());

			GuiInventory.drawEntityOnScreen(13, 43, 22, entity.rotationYaw, entity.rotationPitch, entity);
			mc.fontRendererObj.drawStringWithShadow(entity.getName(), 28, 4, -1);
			Gui.rect(28, 0 + 36, 108, 7, new Color(20, 20, 20, 150).hashCode());
			Gui.rect(28, 0 + 36, health * 5.4, 7, HudRenderer.getColor().darker().hashCode());
			Gui.rect(28, 0 + 36, health * 5.4 - 3, 7, hudColor);

			GlStateManager.pushMatrix();
			GlStateManager.scale(2, 2, 1);
			mc.fontRendererObj.drawStringWithShadow(("" + health).replace(".0", "").replace(".", ",")+ "❤", 28 / 2, 17 / 2,
					hudColor);
			GlStateManager.popMatrix();
			break;
		case "Mint":
			length = 140;
			height = 45;
			RenderUtil.drawRoundedRect(0, 0, 140, 45, 15, new Color(255, 255, 255, 55));
			//mc.fontRendererObj.drawStringWithShadow(entity.getName(), 28, 4, -1);
			RenderUtil.drawRoundedRect(41, 0 + 36, 92, 7, 7, new Color(255, 100, 100));
			RenderUtil.drawRoundedRect(39, 0 + 36, sHealth * 5 - 3, 7, 7, new Color(0, 255, 100));

			RenderUtil.drawPlayerHead(entity, 5, 14, 7);
			
			FontUtil.big.drawString(Double.toString(health).replace(".0", ""), 20, 3,
					-1);
			break;
		}
		GlStateManager.popMatrix();
		
		if(sHealth < entity.getHealth())
			sHealth = entity.getHealth();
		
		if(sHealth > entity.getHealth())
			sHealth -= 0.01 + (sHealth - health) / smoothSpeed;
	}
}
