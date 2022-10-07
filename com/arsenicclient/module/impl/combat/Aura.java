package com.arsenicclient.module.impl.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.arsenicclient.Arsenic;
import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.arsenicclient.settings.impl.BooleanSetting;
import com.arsenicclient.settings.impl.ModeSetting;
import com.arsenicclient.settings.impl.NumberSetting;
import com.arsenicclient.utils.impl.RotationUtil;
import com.arsenicclient.utils.impl.system.Clock;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;


@ModuleInfo(name = "Aura", description = "Attacks for you", category = Category.COMBAT)
public class Aura extends Module {

	public ModeSetting mode = new ModeSetting("Mode", "Switch", "Switch", "Single", "Multi");
	public ModeSetting smode = new ModeSetting("Switch Mode", "HurtTime", "HurtTime", "Health", "Distance");
	public NumberSetting range = new NumberSetting("Range", 3.2f, 1, 6, 1);
	public NumberSetting cps = new NumberSetting("CPS", 14, 1, 20, 1);
	public static ModeSetting block = new ModeSetting("Block", "Vanilla", "Vanilla", "Watchdog");
	public BooleanSetting player = new BooleanSetting("Player", true);
	public BooleanSetting mob = new BooleanSetting("Mob", false);
	public BooleanSetting animal = new BooleanSetting("Animal", false);
	public BooleanSetting invisible = new BooleanSetting("Invisibles", false);

	public List<Entity> targets = new ArrayList<Entity>();
	private final List<Entity> bots = new ArrayList<Entity>();
	
	private final Clock clock = new Clock();
	
	public Aura() {
		this.registerSettings(mode, smode, range, cps, block, player, mob, animal, invisible);
	}
	
    @Override
    public void onEnable() {
       bots.clear();
    }
    
    @Subscribe
	public void onPreMotion(EventPreMotion event) {
		if(!this.isEnabled()) return;
		if(targets.isEmpty() || targets == null) mc.gameSettings.keyBindUseItem.pressed = Mouse.isButtonDown(1);
			
		//add Living Entity's to List
		targets = mc.theWorld.loadedEntityList;
		targets = targets.stream()
				.filter(entity -> entity.getDistanceToEntity(mc.thePlayer) <= range.getValue()
						&& entity != mc.thePlayer && !(entity instanceof EntityDragon) && entity instanceof EntityLivingBase && !bots.contains(entity) && !(entity instanceof EntityArmorStand))
				.collect(Collectors.toList());

		//Remove Entity's we don't want to attack
		if (!player.isToggled())
			targets = targets.stream().filter(entity -> !(entity instanceof EntityPlayer)).collect(Collectors.toList());

		if (!mob.isToggled())
			targets = targets.stream().filter(entity -> !(entity instanceof EntityMob)).collect(Collectors.toList());

		if (!animal.isToggled())
			targets = targets.stream().filter(entity -> !(entity instanceof EntityAnimal)).collect(Collectors.toList());

		if (!invisible.isToggled())
			targets = targets.stream().filter(entity -> !entity.isInvisible()).collect(Collectors.toList());

		//Switch modes
		if (mode.getMode() == "Switch") {
			switch (smode.getMode()) {
			case "HurtTime":
				targets.sort(Comparator.comparingDouble(t -> {
					return ((EntityLivingBase) t).hurtTime;
				}));
				break;
			case "Health":
				targets.sort(Comparator.comparingDouble(t -> {
					return ((EntityLivingBase) t).getHealth();
				}));
				break;
			case "Distance":
				targets.sort(Comparator.comparingDouble(t -> {
					return mc.thePlayer.getDistanceToEntity(t);
				}));
				break;
			}
		}
		
		int count = 0;
		for (Entity target : targets) {
			if (count != 0 && mode.getMode() != "Multi")
				return;

			event.setYaw(doRotations(target)[0]);
			event.setPitch(doRotations(target)[1]);

			if(clock.getTime() > ((long)(1000 / cps.getValue()))) {
					if (target.getDistanceToEntity(mc.thePlayer) <= range.getValue()) {
						hit(target);
					}
					if (target == targets.get(targets.size() - 1) || mode.getMode() != "Multi")
						clock.reset();
			}
			block(target);
			count++;
		}
		
		mc.thePlayer.rotationYawHead = event.getYaw();
		mc.thePlayer.prevRotationYawHead = event.getYaw();
		mc.thePlayer.rotationPitchHead = event.getPitch();
		if(event.getYaw() != mc.thePlayer.rotationYaw) {
		mc.thePlayer.renderYawOffset = event.getYaw();
		mc.thePlayer.prevRenderYawOffset = event.getYaw();
		}
    }
    
    private void hit(Entity entity) {	
    	mc.thePlayer.swingItem();
		mc.playerController.attackEntity(mc.thePlayer, entity);
		mc.playerController.updateController();
	}
    
    private void block(Entity entity) {
		switch(block.getMode()) {
		case "Vanilla":
			mc.gameSettings.keyBindUseItem.pressed = true;
			if(mc.thePlayer.inventory.getCurrentItem() != null)
			mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
			break;
		case "Watchdog":
			mc.gameSettings.keyBindUseItem.pressed = mc.thePlayer.ticksExisted % 3 == 0 ? true : false;
			break;
		}
	}
    
    public float[] doRotations(Entity target) {
    	float yaw = (float) MathHelper.wrapAngleTo180_double((float) (RotationUtil.getRotations(target)[0]));
    	float pitch = (float) MathHelper.wrapAngleTo180_double((float) (RotationUtil.getRotations(target)[1]));
    	
    	return new float[] {yaw, pitch};
    }
}
