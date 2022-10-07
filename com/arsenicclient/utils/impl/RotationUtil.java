package com.arsenicclient.utils.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class RotationUtil {

	public static float[] getRotations(Entity entity) {
		Minecraft mc = Minecraft.getMinecraft();
		double xx = entity.posX - mc.thePlayer.posX;
		double yy = entity.posY - mc.thePlayer.posY;
		double zz = entity.posZ - mc.thePlayer.posZ;
		
		double distance = MathHelper.sqrt_double(xx * xx + zz * zz);
		double yaw = (Math.atan2(zz, xx) * 180 / Math.PI) - 90.0f;
		double pitch = ((float) -Math.atan2(yy, distance) * 180 / Math.PI) + 20;
		
		return new float[] {(float) yaw, (float) pitch};
	}
	
}
