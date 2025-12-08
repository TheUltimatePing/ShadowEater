package com.shadowEater;

public class Dithering {
    // class used make a descent image with limited colors
    // we define all the dithering we can, base on : https://en.wikipedia.org/wiki/Dither

    // we need to access the different colors fast
    private static int getAlpha(int c) {
        return (c >> 24) & 0xff;
    }

    private static int getRed(int c) {
        return (c >> 16) & 0xff;
    }

    private static int getGreen(int c) {
        return (c >> 8) & 0xff;
    }

    private static int getBlue(int c) {
        return (c & 0xff);
    }

    // TODO: optimize the converter (and make it work)
    public static int[][] FloydSteinberg(int[][] image) {
        int oldPixel; // pixel with true color
        int newPixel; // pixel that got averaged

        for (int x = 1; x<image.length-1; x++) {
            for (int y = 0; y<image[x].length-1; y++) {
                oldPixel = image[x][y];
                newPixel = Converter.closestColor(image[x][y]);

                int errR = getRed(oldPixel) - getRed(newPixel);
                int errG = getGreen(oldPixel) - getGreen(newPixel);
                int errB = getBlue(oldPixel) - getBlue(newPixel);

                image[x+1][y] = applyError(image[x+1][y], errR, errG, errB, 7);
                image[x-1][y+1] = applyError(image[x-1][y+1], errR, errG, errB, 3);
                image[x][y+1] = applyError(image[x][y+1], errR, errG, errB, 5);
                image[x+1][y+1] = applyError(image[x+1][y+1], errR, errG, errB, 1);
            }
        }
        return image;
    }

    private static int applyError(int pixel, int errR, int errG, int errB, int w) {
        int r = getRed(pixel);
        int g = getGreen(pixel);
        int b = getBlue(pixel);

        r = Math.max(0, Math.min(255, r + errR * w / 16));
        g = Math.max(0, Math.min(255, g + errG * w / 16));
        b = Math.max(0, Math.min(255, b + errB * w / 16));

        return (0xff << 24) | (r << 16) | (g << 8) | b;
    }

    // pure black and white
    // TODO : make is work
    public static int[][] fixedThreshold(int[][] image) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[x].length; y++) {
                int currentColor = image[x][y];
                // return black or white
                image[x][y] = ((double) (getRed(currentColor) + getGreen(currentColor) + getBlue(currentColor)) /3 >= 0x80 ? 0xffffffff : 0xff000000);
            }
        }

        return image;
    }
}
