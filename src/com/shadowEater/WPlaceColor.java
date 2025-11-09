package com.shadowEater;

import java.awt.*;

public enum WPlaceColor {
    BLACK("Black", new Color(0x0, 0x0, 0x0), false),
    DARK_GRAY("Dark Gray", new Color(0x3c, 0x3c, 0x3c), false),
    GRAY("Gray", new Color(0x78, 0x78, 0x78), false),
    MEDIUM_GRAY("Medium Gray", new Color(0xaa, 0xaa, 0xaa), true),
    LIGHT_GRAY("Light Gray", new Color(0xd2, 0xd2, 0xd2), false),
    WHITE("White", new Color(0xff, 0xff, 0xff), false),
    DEEP_RED("Deep red", new Color(0x60, 0x00, 0x18), false),
    DARK_RED("Dark red", new Color(0xa5, 0x0e, 0x1e), true),
    RED("Red", new Color(0xed, 0x1c, 0x24), false),
    LIGHT_RED("Light red", new Color(0xfa, 0x80, 0x72), true),
    DARK_ORANGE("Dark orange", new Color(0xe4, 0x5c, 0x1a), true),
    ORANGE("Orange", new Color(0xff, 0x7f, 0x27), false),
    GOLD("Gold", new Color(0xf6, 0xaa, 0x09), false),
    YELLOW("Yellow", new Color(0xf9, 0xdd, 0x3b), false),
    LIGHT_YELLOW("Light yellow", new Color(0xff, 0xfa, 0xbc), false),
    DARK_GOLDENROD("Dark goldenrod", new Color(0x9c, 0x84, 0x31), true),
    GOLDENROD("Goldenrod", new Color(0xc5, 0xad, 0x31), true),
    LIGHT_GOLDENROD("Light goldenrod", new Color(0xe8, 0xd4, 0x5f), true),
    DARK_OLIVE("Dark olive", new Color(0x4a, 0x6b, 0x3a), true),
    OLIVE("Olive", new Color(0x5a, 0x94, 0x4a), true),
    LIGHT_OLIVE("Light olive", new Color(0x84, 0xc5, 0x73), true),
    DARK_GREEN("Dark green", new Color(0x0e, 0xb9, 0x68), false),
    GREEN("Green", new Color(0x13, 0xe6, 0x7b), false),
    LIGHT_GREEN("Light green", new Color(0x87, 0xff, 0x5e), false),
    DARK_TEAL("Dark teal", new Color(0x0c, 0x81, 0x6e), false),
    TEAL("Teal", new Color(0x10, 0xae, 0xa6), false),
    LIGHT_TEAL("Light teal", new Color(0x13, 0xe1, 0xbe), false),
    DARK_CYAN("Dark cyan", new Color(0x0f, 0x79, 0x9f), true),
    CYAN("Cyan", new Color(0x60, 0xf7, 0xf2), false),
    LIGHT_CYAN("Light cyan", new Color(0xbb, 0xfa, 0xf2), true),
    DARK_BLUE("Dark blue", new Color(0x28, 0x50, 0x9e), false),
    BLUE("Blue", new Color(0x40, 0x93, 0xe4), false),
    LIGHT_BLUE("Light blue", new Color(0x7d, 0xc7, 0xff), true),
    DARK_INDIGO("Dark indigo", new Color(0x4d, 0x31, 0xb8), true),
    INDIGO("Indigo", new Color(0x6b, 0x50, 0xf6), false),
    LIGHT_INDIGO("Light indigo", new Color(0x99, 0xb1, 0xfb), false),
    DARK_SLATE_BLUE("Dark slate blue", new Color(0x4a, 0x42, 0x84), true),
    SLATE_BLUE("Slate Blue", new Color(0x7a, 0x71, 0xc4), true),
    LIGHT_SLATE_BLUE("Light slate blue", new Color(0xb5, 0xae, 0xf1), true),
    DARK_PURPLE("Dark purple", new Color(0x78, 0x0c, 0x99), false),
    PURPLE("Purple", new Color(0xaa, 0x38, 0xb9), false),
    LIGHT_PURPLE("Light purple", new Color(0xe0, 0x9f, 0xf9), false),
    DARK_PINK("Dark pink", new Color(0xcb, 0x00, 0x7a), false),
    PINK("Pink", new Color(0xec, 0x1f, 0x80), false),
    LIGHT_PINK("Light pink", new Color(0xf3, 0x8d, 0xa9), false),
    DARK_PEACH("Dark peach", new Color(0x9b, 0x52, 0x49), true),
    PEACH("Peach", new Color(0xd1, 0x80, 0x78), true),
    LIGHT_PEACH("Light peach", new Color(0xfa, 0xb6, 0xa4), true),
    DARK_BROWN("Dark brown", new Color(0x68, 0x46, 0x34), false),
    BROWN("Brown", new Color(0x95, 0x68, 0x34), false),
    LIGHT_BROWN("Light brown", new Color(0xdb, 0xa4, 0x63), true),
    DARK_TAN("Dark tan", new Color(0x7b, 0x63, 0x52), true),
    TAN("Tan", new Color(0x9c, 0x84, 0x6b), true),
    LIGHT_TAN("Light tan", new Color(0xd6, 0xb5, 0x94), true),
    DARK_BEIGE("Dark beige", new Color(0xd1, 0x80, 0x51), true),
    BEIGE("Beige", new Color(0xf8, 0xb2, 0x77), false),
    LIGHT_BEIGE("Light beige", new Color(0xff, 0xc5, 0xa5), true),
    DARK_STONE("Dark stone", new Color(0x6d, 0x64, 0x3f), true),
    STONE("Stone", new Color(0x94, 0x8c, 0x6b), true),
    LIGHT_STONE("Light stone", new Color(0xcd, 0xc5, 0x9e), true),
    DARK_SLATE("Dark slate", new Color(0x33, 0x39, 0x41), true),
    SLATE("Slate", new Color(0x6d, 0x75, 0x8d), true),
    LIGHT_SLATE("Light slate", new Color(0xb3, 0xb9, 0xd1), true),
    TRANSPARENT("Transparent", new Color(0x0, 0x0, 0x0, 0x0), false); // special color

    private final String name;
    private final Color color;
    private final boolean paid;

    WPlaceColor(String name, Color color, boolean paid) {
        this.name = name;
        this.color = color;
        this.paid = paid;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public boolean getIsPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return name;
    }
}