package com.shadowEater;

public enum WPlaceColor {
    BLACK("Black", 0xff000000, false),
    DARK_GRAY("Dark Gray",0xff3c3c3c, false),
    GRAY("Gray",0xff787878, false),
    MEDIUM_GRAY("Medium Gray",0xffaaaaaa, true),
    LIGHT_GRAY("Light Gray",0xffd2d2d2, false),
    WHITE("White",0xffffffff, false),
    DEEP_RED("Deep red", 0xff600018, false),
    DARK_RED("Dark red",0xffa50e1e, true),
    RED("Red", 0xffed1c24, false),
    LIGHT_RED("Light red", 0xfffa8072, true),
    DARK_ORANGE("Dark orange", 0xffe45c1a, true),
    ORANGE("Orange", 0xffff7f27, false),
    GOLD("Gold", 0xfff6aa09, false),
    YELLOW("Yellow", 0xfff9dd3b, false),
    LIGHT_YELLOW("Light yellow", 0xfffffabc, false),
    DARK_GOLDENROD("Dark goldenrod", 0xff9c8431, true),
    GOLDENROD("Goldenrod", 0xffc5ad31, true),
    LIGHT_GOLDENROD("Light goldenrod", 0xffe8d45f, true),
    DARK_OLIVE("Dark olive", 0xff4a6b3a, true),
    OLIVE("Olive", 0xff5a944a, true),
    LIGHT_OLIVE("Light olive", 0xff84c573, true),
    DARK_GREEN("Dark green", 0xff0eb968, false),
    GREEN("Green", 0xff13e67b, false),
    LIGHT_GREEN("Light green", 0xff87ff5e, false),
    DARK_TEAL("Dark teal", 0xff0c816e, false),
    TEAL("Teal", 0xff10aea6, false),
    LIGHT_TEAL("Light teal", 0xff13e1be, false),
    DARK_CYAN("Dark cyan", 0xff0f799f, true),
    CYAN("Cyan", 0xff60f7f2, false),
    LIGHT_CYAN("Light cyan", 0xffbbfaf2, true),
    DARK_BLUE("Dark blue", 0xff28509e, false),
    BLUE("Blue", 0xff4093e4, false),
    LIGHT_BLUE("Light blue", 0xff7dc7ff, true),
    DARK_INDIGO("Dark indigo", 0xff4d31b8, true),
    INDIGO("Indigo", 0xff6b50f6, false),
    LIGHT_INDIGO("Light indigo", 0xff99b1fb, false),
    DARK_SLATE_BLUE("Dark slate blue", 0xff4a4284, true),
    SLATE_BLUE("Slate Blue", 0xff7a71c4, true),
    LIGHT_SLATE_BLUE("Light slate blue", 0xffb5aef1, true),
    DARK_PURPLE("Dark purple", 0xff780c99, false),
    PURPLE("Purple", 0xffaa38b9, false),
    LIGHT_PURPLE("Light purple", 0xffe09ff9, false),
    DARK_PINK("Dark pink", 0xffcb007a, false),
    PINK("Pink", 0xffec1f80, false),
    LIGHT_PINK("Light pink", 0xfff38da9, false),
    DARK_PEACH("Dark peach", 0xff9b5249, true),
    PEACH("Peach", 0xffd18078, true),
    LIGHT_PEACH("Light peach", 0xfffab6a4, true),
    DARK_BROWN("Dark brown", 0xff684634, false),
    BROWN("Brown", 0xff956834, false),
    LIGHT_BROWN("Light brown", 0xffdba463, true),
    DARK_TAN("Dark tan", 0xff7b6352, true),
    TAN("Tan", 0xff9c846b, true),
    LIGHT_TAN("Light tan", 0xffd6b594, true),
    DARK_BEIGE("Dark beige", 0xffd18051, true),
    BEIGE("Beige", 0xfff8b277, false),
    LIGHT_BEIGE("Light beige", 0xffffc5a5, true),
    DARK_STONE("Dark stone", 0xff6d643f, true),
    STONE("Stone", 0xff948c6b, true),
    LIGHT_STONE("Light stone", 0xffcdc59e, true),
    DARK_SLATE("Dark slate", 0xff333941, true),
    SLATE("Slate", 0xff6d758d, true),
    LIGHT_SLATE("Light slate", 0xffb3b9d1, true),
    TRANSPARENT("Transparent", 0x00000000, false); // special color

    private final String name;
    private final int color;
    private final boolean paid;

    WPlaceColor(String name, int color, boolean paid) {
        this.name = name;
        this.color = color;
        this.paid = paid;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
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