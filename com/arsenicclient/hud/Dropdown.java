package com.arsenicclient.hud;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.arsenicclient.Arsenic;
import com.arsenicclient.font.impl.FontUtil;
import com.arsenicclient.hud.renderer.HudRenderer;
import com.arsenicclient.module.Category;
import com.arsenicclient.settings.Setting;
import com.arsenicclient.settings.impl.BooleanSetting;
import com.arsenicclient.settings.impl.KeySetting;
import com.arsenicclient.settings.impl.ModeSetting;
import com.arsenicclient.settings.impl.NumberSetting;
import com.arsenicclient.utils.impl.RenderUtil;
import com.arsenicclient.module.Module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class Dropdown extends GuiScreen {
		private Minecraft mc = Minecraft.getMinecraft();

		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			draw(mouseX, mouseY, cType.DRAW);
		}

		protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
			draw(mouseX, mouseY, cType.CLICK);
		}

		protected void mouseReleased(int mouseX, int mouseY, int state) {
			draw(mouseX, mouseY, cType.RELEASE);
		}

		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			super.keyTyped(typedChar, keyCode);
		}

		public void draw(int mouseX, int mouseY, cType type) {
			double w = 90;
			double gap = 19;
			
			int ccount = 0;
			for (Category c : Category.values()) {
				c.posX = 10 + ((w + 2) * ccount);
				c.posY = 20;
				Color color1 = new Color(15, 15, 15);
				Color color2 = new Color(25, 25, 25);
				Color highlight = HudRenderer.getHUD().getColor();
				HudRenderer.getHUD().setColor(Color.red);
				
				RenderUtil.drawRect(c.posX, c.posY, w, gap, color1);
				FontUtil.two.drawCenteredString(c.name, (float) ((int) c.posX + w / 2), (int) c.posY + 6, -1);

				int count = 1;
				boolean lit = false;;
				for (Module m : Arsenic.getArsenic().getModuleManager().getModules()) {
					if (m.getCategory() == c) {
						for(float i=0; i < gap; i += 0.5f) {
						float[] hsb = new float[3];
						Color.RGBtoHSB(highlight.getRed(), highlight.getGreen(), highlight.getBlue(), hsb);
						RenderUtil.drawRect(c.posX, c.posY + i + count * gap, w, 0.5f, m.isEnabled() ? lit ? highlight : new Color(Color.HSBtoRGB(hsb[0], hsb[1], (float) (0.5f + (i / gap / 2)))) : color2);
						}
						RenderUtil.drawRect(c.posX, c.posY + count * gap, 1, gap, color1);
						RenderUtil.drawRect(c.posX + w - 1, c.posY + count * gap, 1, gap, color1);
						FontUtil.normal.drawSmoothString(m.getName(), c.posX + 4, (float) (c.posY + 7 + count * gap), -1);

						if (RenderUtil.isOver(c.posX, c.posY + count * gap, w, gap, mouseX, mouseY)
								&& type == cType.CLICK) {
							if (Mouse.isButtonDown(0))
								m.toggle();
							else
							if (Mouse.isButtonDown(1))
								m.setExpanded(!m.isExpanded());
						}

						count++;

						if (m.isExpanded()) {
							for (Setting s : m.getSettings()) {
								RenderUtil.drawRect(c.posX, c.posY + count * gap, w, gap, color2);
								RenderUtil.drawRect(c.posX, c.posY + count * gap, 1, gap, color1);
								RenderUtil.drawRect(c.posX + w - 1, c.posY + count * gap, 1, gap, color1);
								
								if (s instanceof KeySetting) {
									KeySetting k = (KeySetting) s;

									FontUtil.normal.drawSmoothString("KEY: " + Keyboard.getKeyName(k.getCode()), c.posX + 2,
											(float) (c.posY + 4 + count * gap), -1);

									if (RenderUtil.isOver(c.posX, c.posY + count * gap, w, gap, mouseX, mouseY)
											&& Mouse.isButtonDown(0)) {
										int key = Keyboard.getEventKey();
										if (key > 0)
											m.setKey(key);
									}
								}

								if (s instanceof BooleanSetting) {
									BooleanSetting b = (BooleanSetting) s;
									if (b.isToggled())
										RenderUtil.drawRect(c.posX, c.posY + count * gap, w, gap, highlight);

									FontUtil.normal.drawSmoothString(b.name + " " + b.isToggled(), c.posX + 2,
											(float) (c.posY + 4 + count * gap), -1);

									if (RenderUtil.isOver(c.posX, c.posY + count * gap, w, gap, mouseX, mouseY)
											&& type == cType.CLICK) {
										b.toggle();
									}
								}
								if (s instanceof ModeSetting) {
									ModeSetting mode = (ModeSetting) s;

									FontUtil.normal.drawSmoothString(mode.name + " " + mode.getMode(), c.posX + 2,
											(float) (c.posY + 4 + count * gap), -1);

									if (RenderUtil.isOver(c.posX, c.posY + count * gap, w, gap, mouseX, mouseY)
											&& type == cType.CLICK) {
										mode.next();
									}
								}
								if (s instanceof NumberSetting) {
									NumberSetting n = (NumberSetting) s;

									double nwidth = ((n.getValue() - n.getMin()) / (n.getMax() - n.getMin())) * w;

									RenderUtil.drawRect(c.posX, c.posY + count * gap, nwidth, gap, highlight);
									FontUtil.normal.drawSmoothString(n.name + " " + n.getValue(), c.posX + 2,
											(float) (c.posY + 4 + count * gap), -1);

									if (RenderUtil.isOver(c.posX, c.posY + count * gap, w, gap, mouseX, mouseY)
											&& Mouse.isButtonDown(0)) {
										double value = n.getMin() + ((mouseX - c.posX) / (w)) * (n.getMax() - n.getMin());
										n.setValue((float) value);
									}
								}

								count++;
							}
						}
						lit = m.isEnabled();
					}
				}
				RenderUtil.drawRect(c.posX, c.posY + count * gap, w, 1, color1);
				ccount++;
			}
		}

		public void onGuiClosed() {
			super.onGuiClosed();
		}

		public boolean doesGuiPauseGame() {
			return false;
		}

		enum cType {
			DRAW, CLICK, RELEASE
		}
}
