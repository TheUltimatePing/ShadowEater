package com.shadowEater;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

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
    private static int closestColor(int colorInput) {

        int closest = 0x0; // start transparent

        double currentMin = 0;

        // index for the closest color
        int minColor = 0;
        int i = 0;

        WPlaceColor[] colorList = WPlaceColor.values();

        double[] min = new double[colorList.length];
        Arrays.fill(min, -1); // fill all the array with -1

        // we get te area of the alphaChanel and then check if it's a solid color
        final boolean hasAlphaChannel = (((colorInput & 0xff000000 ) >>> 24) != 0xFF);

        // input color
        int ib = ((colorInput & 0xff));
        int ig = ((colorInput >> 8) & 0xff);
        int ir = ((colorInput >> 16) & 0xff);

        // we check if at least one of the color is selected
        // we check if the provided color is transparent
        if (ShadowApp.getChoice().length > 0 && !hasAlphaChannel) {
            // we get each color in the WPlaceColor
            for (WPlaceColor c : colorList) {

                Color color = c.getColor();

                // get each main color in the WplaceColor

                int cb = color.getBlue();
                int cg = color.getGreen();
                int cr = color.getRed();

                // we put each value into an array
                int blue = (cb - ib);
                int green = (cg - ig);
                int red = (cr - ir);

                // distance of the color with the reference color
                min[i] = (Math.sqrt(blue*blue + green*green + red*red));

                i++;
            }

            // we search the smallest score
            for (i = 0; i < min.length; i++) {
                // we assing the first valid value
                currentMin = getFirstMin(min);
                if (min[i] < currentMin && min[i] > -1) {
                    // min color is used to keep the index of the smallest color
                    minColor = i;
                    // current min is used to keep the smallest double
                    currentMin = min[i];
                }
            }

            // we merge the color
            closest = (0xff) << 24 |
                        (colorList[minColor].getColor().getRed() & 0xff) << 16 |
                        (colorList[minColor].getColor().getGreen() & 0xff) << 8 |
                        (colorList[minColor].getColor().getBlue() & 0xff);

        } // if the color has an alpha channel then it's a transparent pixel


        return closest;
    }

    // if it's called then at least one of the option si selected
    private static double getFirstMin(double[] min) {
        int i = 0;
        while (min[i] < 0 && i < ShadowApp.getChoice().length) i++;
        return min[i];
    }
}
