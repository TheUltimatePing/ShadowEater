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
    BLUE("Blue", new Color(0x40, 0x93, 0xe4), false);

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