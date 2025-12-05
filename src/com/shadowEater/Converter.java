package com.shadowEater;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Converter {
    public Converter() {}
    // transform an image and return an array for easier/quicker manipulation
    // don't use .rgb() because it's extra slow
    public static int[][] bufferedToArray(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += 0xff000000; // 255 alpha
                      // a, r, g, b
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }

    // transform an array to an image type
    public static BufferedImage arrayToBufferedImage(int[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // slow maybe to change
                image.setRGB(x, y, pixels[y][x]);
            }
        }

        return image;
    }

    // use a tab to know what color the user selected
    public static int[][] convertImage(int[][] image) {
        for (int i = 0; i<image.length; i++) {
            for (int j = 0; j<image[i].length; j++) {
                image[i][j] = closestColor(image[i][j]);
            }
        }
        return image;
    }

    // for a pixel give the closest color in the list enabled by the user if at least one is enabled
    // TODO : OPTIMIZE THE COLOR CHECKER
    // public => we use it inside for the dithering algorithm
    public static int closestColor(int colorInput) {
        // by default Transparent
        int closest = 0x0;

        // is the pixel transparent
        boolean isTransparent = (getAlpha(colorInput) != 0xFF);

        if (ShadowApp.getChoice().length == 0 || isTransparent) {
            return closest;
        }

        // extract r, g, b value from the inputted int
        int ib = (colorInput & 0xff);
        int ig = (colorInput >> 8) & 0xff;
        int ir = (colorInput >> 16) & 0xff;

        WPlaceColor[] colorList = WPlaceColor.values();
        double currentMin = Double.MAX_VALUE;
        int minColor = 0;

        for (int i = 0; i < colorList.length; i++) {
            int c = colorList[i].getColor();
            int dr = getRed(c) - ir;
            int dg = getGreen(c) - ig;
            int db = getBlue(c) - ib;

            double dist = Math.sqrt(dr*dr + dg*dg + db*db);
            if (dist < currentMin) {
                currentMin = dist;
                minColor = i;
            }
        }

        // rebuild the color from the r, b and b color plus the alpha at 255
        int best = colorList[minColor].getColor();
        closest = (0xff << 24) | (getRed(best) << 16) | (getGreen(best) << 8) | getBlue(best);

        return closest;
    }

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
}