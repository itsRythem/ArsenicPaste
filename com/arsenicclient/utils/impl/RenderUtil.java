package com.arsenicclient.utils.impl;

import net.minecraft.client.Minecraft;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;


public class RenderUtil {

    public static Minecraft mc = Minecraft.getMinecraft();

    public static void drawRect(double left, double top, double right, double bottom, Color colorr)
    {
    	int color = colorr.hashCode();
		right = left + right;
		bottom = top + bottom;

		   if (left < right)
	        {
	            double i = left;
	            left = right;
	            right = i;
	        }

	        if (top < bottom)
	        {
	        	double j = top;
	            top = bottom;
	            bottom = j;
	        }

	        float f3 = (float)(color >> 24 & 255) / 255.0F;
	        float f = (float)(color >> 16 & 255) / 255.0F;
	        float f1 = (float)(color >> 8 & 255) / 255.0F;
	        float f2 = (float)(color & 255) / 255.0F;
	        Tessellator tessellator = Tessellator.getInstance();
	        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
	        GlStateManager.enableBlend();
	        GlStateManager.disableTexture2D();
	        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	        GlStateManager.color(f, f1, f2, f3);
	        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
	        worldrenderer.pos((double)left, (double)bottom, 0.0D).endVertex();
	        worldrenderer.pos((double)right, (double)bottom, 0.0D).endVertex();
	        worldrenderer.pos((double)right, (double)top, 0.0D).endVertex();
	        worldrenderer.pos((double)left, (double)top, 0.0D).endVertex();
	        tessellator.draw();
	        GlStateManager.enableTexture2D();
	        GlStateManager.disableBlend();
    }

    public static void drawRoundedRect(double x, double y, double width, double height, double radius, Color color) {    
		if(height >= radius && width >= radius) {
		GlStateManager.pushMatrix();
		width = x + width;
		height = y + height;
		GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        double x1 = width;
        double y1 = height;
        glPushAttrib(0);
        glScaled(0.1, 0.1, 0.1);

        x *= 10;
        y *= 10;
        x1 *= 10;
        y1 *= 10;
        radius *= 5;

        glDisable(GL_TEXTURE_2D);
        glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        glEnable(GL_LINE_SMOOTH);

        glBegin(GL_TRIANGLE_FAN);
        drawCirclePart(x, y, x1, y1, radius);
        glEnd();

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_LINE_SMOOTH);
        glEnable(GL_TEXTURE_2D);

        glScaled(10, 10, 10);

        glPopAttrib();
        glColor4f(1, 1, 1, 1);
        GlStateManager.disableBlend();
        x /= 10;
        y /= 10;
        x1 /= 10;
        y1 /= 10;
        radius /= 5;
        GlStateManager.popMatrix();
        drawRoundedRectOutline(x, y, width - x, height - y, radius, color, 1);
		}
    }
	
	public static void drawRoundedRectOutline(double x, double y, double width, double height, double radius, Color color, float lineWidth) {    
		GlStateManager.pushMatrix();
		width = x + width;
		height = y + height;
			
		int random = (int) ((System.currentTimeMillis() / 5) % 160);
		GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        double x1 = width;
        double y1 = height;
        glPushAttrib(0);
        glScaled(0.1, 0.1, 0.1);

        x *= 10;
        y *= 10;
        x1 *= 10;
        y1 *= 10;
        radius *= 5;

        glDisable(GL_TEXTURE_2D);
        glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        glEnable(GL_LINE_SMOOTH);
        glLineWidth(lineWidth);
        
        glBegin(GL_LINE_STRIP);

        int count = 0;
        glVertex2d(x + radius, y);
        glVertex2d(x1 - radius, y);
        
        for (int i = 0; i <= 90; i += 3) {
            glVertex2d(x + radius + +(Math.sin((i * Math.PI / 180)) * (radius * -1)), y + radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
            count++;
        }

        for (int i = 90; i <= 180; i += 3) {
        	glVertex2d(x + radius + (Math.sin((i * Math.PI / 180)) * (radius * -1)), y1 - radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
            count++;
        }

        for (int i = 0; i <= 90; i += 3) {
        	glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y1 - radius + (Math.cos((i * Math.PI / 180)) * radius));
            count++;
        }

        for (int i = 90; i <= 180; i += 3) {
        	glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y + radius + (Math.cos((i * Math.PI / 180)) * radius));
            count++;
        }

        glEnd();

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_LINE_SMOOTH);
        glEnable(GL_TEXTURE_2D);

        glScaled(10, 10, 10);

        glPopAttrib();
        glColor4f(1, 1, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
	
	public static void drawCirclePart(double x, double y, double x1, double y1, double radius) {
		for (double i = 0; i <= 90; i += 0.5) {
            glVertex2d(x + radius + +(Math.sin((i * Math.PI / 180)) * (radius * -1)), y + radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
        }

        for (double i = 90; i <= 180; i += 0.5) {
            glVertex2d(x + radius + (Math.sin((i * Math.PI / 180)) * (radius * -1)), y1 - radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
        }

        for (double i = 0; i <= 90; i += 0.5) {
            glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y1 - radius + (Math.cos((i * Math.PI / 180)) * radius));
        }

        for (double i = 90; i <= 180; i += 0.5) {
            glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y + radius + (Math.cos((i * Math.PI / 180)) * radius));
        }
	}
	
	public static void drawPlayerHead(EntityLivingBase player, double x, double y, float scale) {
		glPushMatrix();
		glColor3f(1, 1, 1);
		glScaled(scale, scale, 1);
		glEnable(GL_LINE_SMOOTH);
		GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		mc.getTextureManager().bindTexture(new ResourceLocation("Client/blank.png"));
		GL11.glTranslated(x / scale, y / scale, 1);
		if(player instanceof AbstractClientPlayer) {
			mc.getTextureManager().bindTexture(((AbstractClientPlayer)player).getLocationSkin());
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 36, 36, 4, 4, 32, 32);
		} else {
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 4, 4, 4, 4);
		}
		
		glPopMatrix();
	}
	
	public static void drawRoundedRectProgress(double x, double y, double width, double height, double radius, Color color, float lineWidth, int lineLength) {    
		GlStateManager.pushMatrix();
		width = x + width;
		height = y + height;
		
		int offset = lineLength;
		GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        double x1 = width;
        double y1 = height;
        float f = (color.getRGB() >> 24 & 0xFF) / 255.0F;
        float f1 = (color.getRGB() >> 16 & 0xFF) / 255.0F;
        float f2 = (color.getRGB() >> 8 & 0xFF) / 255.0F;
        float f3 = (color.getRGB() & 0xFF) / 255.0F;
        glPushAttrib(0);
        glScaled(0.1, 0.1, 0.1);

        x *= 10;
        y *= 10;
        x1 *= 10;
        y1 *= 10;
        radius *= 5;

        glDisable(GL_TEXTURE_2D);
        glColor4f(f1, f2, f3, f);
        glEnable(GL_LINE_SMOOTH);
        glLineWidth(lineWidth);
        
        glBegin(GL_LINE_STRIP);

        int count = 0;
        glVertex2d(x + radius, y);
        glVertex2d(x - radius + width * 10.2f, y);
        
        for (int i = 0; i <= 90; i += 3) {
        	if(count < offset)
            glVertex2d(x + radius + (Math.sin((i * Math.PI / 180)) * (radius * -1)), y + radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
            count++;
        }

        for (int i = 90; i <= 180; i += 3) {
        	if(count < offset)
        	glVertex2d(x + radius + (Math.sin((i * Math.PI / 180)) * (radius * -1)), y1 - radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
            count++;
        }

        for (int i = 0; i <= 90; i += 3) {
        	if(count < offset)
        	glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y1 - radius + (Math.cos((i * Math.PI / 180)) * radius));
            count++;
        }

        for (int i = 90; i <= 180; i += 3) {
        	if(count < offset)
        	glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y + radius + (Math.cos((i * Math.PI / 180)) * radius));
            count++;
        }

        glEnd();

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_LINE_SMOOTH);
        glEnable(GL_TEXTURE_2D);

        glScaled(10, 10, 10);

        glPopAttrib();
        glColor4f(1, 1, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
	
    public static boolean isOver(double x, double y, double width, double height, int mouseX, int mouseY) {
		if(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height)
			return true;

		return false;

	}
}
