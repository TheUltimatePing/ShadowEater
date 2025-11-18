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
                argb += -16777216; // 255 alpha
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
        System.out.println(colorInput);
        System.out.println(Integer.toBinaryString(colorInput));
        int closest = 0; // start transparent
        int minScore = 0xFFFFFF;
        int i = 0;

        WPlaceColor[] colorList = WPlaceColor.values();

        double[] min = new double[colorList.length];

        final boolean hasAlphaChannel = ((colorInput & 0xff) << 24) != 0;

        // input color
        int ib = (colorInput & 0xff);
        int ig = ((colorInput & 0xff) << 8);
        int ir = ((colorInput & 0xff) << 16);

        System.out.println(ib); System.out.println(Integer.toBinaryString(ib));
        System.out.println(ig); System.out.println(Integer.toBinaryString(ib));
        System.out.println(ir); System.out.println(Integer.toBinaryString(ib));

        // we check if at least one of the color is selected
        if (ShadowApp.getChoice().length > 0) {
            // we check if the provided color is transparent
            if (!hasAlphaChannel) {
                // we get each color in the WPlaceColor
                for (WPlaceColor c : colorList) {

                    Color color = c.getColor();

                    // get each main color in the WplaceColor
                    int cb = color.getBlue();
                    int cg = color.getGreen();
                    int cr = color.getRed();

                    // we put each value into an array
                    min[i] = (Math.sqrt(Math.pow(cb - ib, 2) + Math.pow(cg - ig, 2) + Math.pow(cr - ir, 2)));

                    if (min[i] < minScore) {
                        minScore = i;
                    }
                    i++;
                }

                // we search the smallest score
                for (i = 0; i < min.length; i++) {
                    if (ShadowApp.getChoice()[i] && min[i] < minScore) {
                        minScore = i;
                    }
                }

                // we merge the color
                closest = (colorList[minScore].getColor().getRed() & 0xff) +
                        ((colorList[minScore].getColor().getGreen() & 0xff) << 8) +
                        ((colorList[minScore].getColor().getBlue() & 0xff) << 16);

            } // if the color has an alpha channel then it's a transparent pixel
        }

        return closest;
    }
}
