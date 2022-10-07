package com.arsenicclient.font.impl;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.arsenicclient.hud.renderer.HudRenderer;

@SuppressWarnings("NonAtomicOperationOnVolatileField")
public class FontUtil {
    public static volatile int completed;

    public static FontRenderer normal;
    public static FontRenderer two;
    public static FontRenderer big;
    public static FontRenderer icons;

    private static Font normal_;
    private static Font two_;
    private static Font big_;
    private static Font small_;
    private static Font icons_;

    private static Font getFont(Map<String, Font> locationMap, String location, int size, int fonttype) {
        Font font = null;

        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(Font.PLAIN, size);
            } else {
                InputStream is = HudRenderer.class.getResourceAsStream("/assets/minecraft/" + location);
                assert is != null;
                font = Font.createFont(Font.TRUETYPE_FONT, is);
                locationMap.put(location, font);
                font = font.deriveFont(fonttype, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("roboto", Font.PLAIN, +size);
        }

        return font;
    }

    public static boolean hasLoaded() {
        return completed >= 3;
    }

    public static void bootstrap() {
        new Thread(() -> {
            Map<String, Font> locationMap = new HashMap<>();
            normal_ = getFont(locationMap, "sfpro.ttf", 19, Font.PLAIN);
            two_ = getFont(locationMap, "roboto.ttf", 20, Font.PLAIN);
            small_ = getFont(locationMap, "sfpro.ttf", 14, Font.BOLD);
            big_ = getFont(locationMap, "roboto.ttf", 26, Font.BOLD);
            icons_ = getFont(locationMap, "entypo.ttf", 50, Font.PLAIN);
            completed++;
        }).start();
        new Thread(() -> {
            Map<String, Font> locationMap = new HashMap<>();
            completed++;
        }).start();
        new Thread(() -> {
            Map<String, Font> locationMap = new HashMap<>();
            completed++;
        }).start();

        while (!hasLoaded()) {
            try {
                // noinspection BusyWait
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        normal = new FontRenderer(normal_, true, true);
        two = new FontRenderer(two_, true, true);
        big = new FontRenderer(big_, true, true);
        icons = new FontRenderer(icons_, true, true);
    }
}