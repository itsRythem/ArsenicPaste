package com.arsenicclient.module;

public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    EXPLOIT("Exploit"),
    MISC("Misc");

    public String name;
    public double posX, posY;

    Category(String name) {
        this.name = name;
    }

}
