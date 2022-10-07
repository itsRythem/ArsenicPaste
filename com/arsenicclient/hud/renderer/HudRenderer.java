package com.arsenicclient.hud.renderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.arsenicclient.Arsenic;
import com.arsenicclient.font.impl.FontUtil;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.impl.render.HUD;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HudRenderer {

    private static Minecraft mc = Minecraft.getMinecraft();

    private static ScaledResolution sr;
    private static Color color = Color.white;
	private static HudRenderer INSTANCE = new HudRenderer();
	private static List<Module> modules = new ArrayList<Module>();
	
	public static HudRenderer getHUD() {

		return INSTANCE;

	}
	
	public static Color getColor() {
		return color;
	}

	public static void setColor(Color color) {
		HudRenderer.color = color;
	}
    
    public static void renderElements() {
        drawArrayList();
        drawTitle();
    }

    private static void drawArrayList() {
    	HUD hud = (HUD)(Arsenic.getArsenic().getModuleManager().getModule("HUD"));
    	modules = Arsenic.getArsenic().getModuleManager().getEnabledModules();
    	
		Comparator<Object> sorted = Comparator.comparingDouble(m -> {
			Module module = (Module)m;
			
			String text = module.getName() + ((((Module) m)).getSuffix() == "" ? " " : "  " + module.getSuffix());
			
			if (!hud.sfx.isToggled()) text = module.getName();
				
			return FontUtil.normal.getStringWidth(text);
		}).reversed();
		modules.sort(sorted);

        int index = 0;

        sr = new ScaledResolution(mc);

        for(final Module m : modules) {

            final double width = FontUtil.normal.getStringWidth(m.getName()) + 2;
            final double height = FontUtil.normal.getHeight() + 7;
            
            Gui.rect(sr.getScaledWidth() - width - 5, (index * height) + 8, width, height, new Color(0, 0, 0, 100).hashCode());
            Gui.rect(sr.getScaledWidth() - 5, (index * height) + 8, 1, height, color.hashCode());


            FontUtil.normal.drawStringWithShadow(m.getName(), sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.getName()) - 6, (float) ((index * height) + 8 + height / 3), color.hashCode());

            index++;



        }

        //RenderUtil.drawRoundedRectangle((sr.getScaledWidth() / 2) - 113, (sr.getScaledHeight() / 2) + 3, (sr.getScaledWidth() / 2) - 3, (sr.getScaledHeight() / 2) + 52, 12, Color.darkGray.darker().darker().darker().getRGB());
    }

    private static void drawTitle() {
        Gui.drawRect(4, 4, FontUtil.two.getStringWidth("Arsenic | " + mc.getSession().getUsername()) + 12, (FontUtil.two.getHeight()*2) + 4, 0x90000000);
        Gui.drawRect(4, 4, FontUtil.two.getStringWidth("Arsenic | " + mc.getSession().getUsername()) + 12, 6, color.hashCode());
        FontUtil.two.drawSmoothString("Arsenic | " + mc.getSession().getUsername(), 8, 9, Color.white.getRGB());
    }
}