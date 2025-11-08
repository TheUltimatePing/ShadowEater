import java.awt.*;

public enum WPlaceColor {
    BLACK("Black", new Color(0x0, 0x0, 0x0)),
    DARK_GRAY("Dark Gray", new Color(0x3c, 0x3c, 0x3c)),
    GRAY("Gray", new Color(0x78, 0x78, 0x78)),
    MEDIUM_GRAY("Medium Gray", new Color(0xaa, 0xaa, 0xaa)),
    LIGHT_GRAY("Light Gray", new Color(0xd2, 0xd2, 0xd2)),
    WHITE("White", new Color(0xff, 0xff, 0xff)),
    DEEP_RED("Deep red", new Color(0x60, 0x00, 0x18)),
    DARK_RED("Dark red", new Color(0xa5, 0x0e, 0x1e)),
    RED("Red", new Color(0xed, 0x1c, 0x24)),
    LIGHT_RED("Light red", new Color(0xfa, 0x80, 0x72)),
    DARK_ORANGE("Dark orange", new Color(0xe4, 0x5c, 0x1a)),
    ORANGE("Orange", new Color(0xff, 0x7f, 0x27)),
    GOLD("Gold", new Color(0xf6, 0xaa, 0x09)),
    YELLOW("Yellow", new Color(0xf9, 0xdd, 0x3b)),
    LIGHT_YELLOW("Light yellow", new Color(0xff, 0xfa, 0xbc)),
    DARK_GOLDENROD("Dark goldenrod", new Color(0x9c, 0x84, 0x31)),
    GOLDENROD("Goldenrod", new Color(0xc5, 0xad, 0x31)),
    LIGHT_GOLDENROD("Light goldenrod", new Color(0xe8, 0xd4, 0x5f)),
    DARK_OLIVE("Dark olive", new Color(0x4a, 0x6b, 0x3a)),
    OLIVE("Olive", new Color(0x5a, 0x94, 0x4a)),
    LIGHT_OLIVE("Light olive", new Color(0x84, 0xc5, 0x73)),
    DARK_GREEN("Dark green", new Color(0x0e, 0xb9, 0x68)),
    GREEN("Green", new Color(0x13, 0xe6, 0x7b)),
    LIGHT_GREEN("Light green", new Color(0x87, 0xff, 0x5e)),
    DARK_TEAL("Dark teal", new Color(0x0c, 0x81, 0x6e)),
    TEAL("Teal", new Color(0x10, 0xae, 0xa6)),
    LIGHT_TEAL("Light teal", new Color(0x13, 0xe1, 0xbe)),
    DARK_CYAN("Dark cyan", new Color(0x0f, 0x79, 0x9f)),
    CYAN("Cyan", new Color(0x60, 0xf7, 0xf2)),
    LIGHT_CYAN("Light cyan", new Color(0xbb, 0xfa, 0xf2)),
    DARK_BLUE("Dark blue", new Color(0x28, 0x50, 0x9e)),
    BLUE("Blue", new Color(0x40, 0x93, 0xe4));

    private final String name;
    private final Color color;

    WPlaceColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }
}