package com.arsenicclient.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.arsenicclient.settings.Setting;
import com.arsenicclient.settings.impl.KeySetting;
import com.arsenicclient.utils.impl.MoveUtil;

import net.minecraft.client.Minecraft;

public class Module {

    protected final Minecraft mc = Minecraft.getMinecraft();
    
    protected final MoveUtil move = new MoveUtil();

    private boolean toggled, expanded;
    protected final String name;
    private String suffix;
    public double posX, posY, x, y;
    
	protected final String description;
    protected final Category category;
    private KeySetting key = new KeySetting(0);

    private List<Setting> settings = new ArrayList<Setting>();

    public Module() {
         name = getClass().getAnnotation(ModuleInfo.class).name();
         description = getClass().getAnnotation(ModuleInfo.class).description();
         category = getClass().getAnnotation(ModuleInfo.class).category();
         this.registerSettings(key);
     }
    
	protected void registerSettings(final Setting... settings) {
		
		Arrays.stream(settings).forEach(s -> this.settings.add(s));
		
	}


    public String getSuffix() {
		return suffix;
	}

    public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
    
	public void toggle() {
        if(toggled == false) {
            toggled = true;
            onEnable();
        } else {
            toggled = false;
            onDisable();
        }
    }
    
    public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<Setting> getSettings() {
		return settings;
	}

	public boolean isEnabled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public int getKey() {
        return key.getCode();
    }
    
    public void setKey(int key) {
        this.key.setCode(key);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }


    public void onEnable() {

    }

    public void onDisable() {

    }
}
