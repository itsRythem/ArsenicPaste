package com.arsenicclient.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import com.arsenicclient.Arsenic;
import com.arsenicclient.event.impl.misc.*;
import com.arsenicclient.module.impl.combat.Aura;
import com.arsenicclient.module.impl.movement.*;
import com.arsenicclient.module.impl.player.Velocity;
import com.arsenicclient.module.impl.render.*;
import com.google.common.eventbus.Subscribe;

public class ModuleManager {
	
	private final ArrayList<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
    	Arsenic.getArsenic().getShuttle().register(this);
    }
    
    /**
     * 
     * <p>
     * Registers Modules and registers them too EventManager
     * 
     */
    public void init() {
    	
    	this.put
    	(
    			
    			new Sprint(),
    			new Flight(),
    			new Speed(),
    			new ClickGUI(),
    			new Animations(),
    			new HUD(),
    			new Aura(),
    			new Velocity(),
    			new NoSlow(),
    			new TargetHUD()
    			
    	);
    	
    	for(final Module m : modules) Arsenic.getArsenic().getShuttle().register(m);
		
    }
    

    private void put(final Module... m) {
    	
		Arrays.stream(m).forEach(module -> modules.add(module));
    	
    }

    public ArrayList<Module> getModules() {
    	
        return modules;
        
    }
    
    public Module getModule(final String name) {

    	for(Module m : this.modules) {

    		if(m.getName().contains(name)) return m;

    	}

    	return null;

    }
    
    public ArrayList<Module> getEnabledModules() {
    	
    	final ArrayList<Module> returnList = new ArrayList<Module>();
    	
    	for(Module m : this.modules) {
    		
    		if(m.isEnabled()) returnList.add(m);
    		
    	}
    	
    	return returnList;
        
    }

    @Subscribe
    public void onKeyEvent(final KeyEvent event) {
        for(Module m : modules) {
        	
            if (event.getKey() == m.getKey())   m.toggle();
            
        }
    }
}
