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

    // TODO: optimize the converter
    public static int[][] FloydSteinberg(int[][] image) {
        int oldPixel; // pixel with true color
        int newPixel; // pixel that got averaged
        int quant_error;

        for (int x = 1; x<image.length-1; x++) {
            for (int y = 0; y<image[x].length-1; y++) {
                oldPixel = image[x][y];
                newPixel = Converter.closestColor(image[x][y]);

                image[x][y] = newPixel;
                quant_error = oldPixel-newPixel;

                // for each color in the pixel
                float r = (newPixel >> 24) & 0xff;
                float g = (newPixel >> 16) & 0xff;
                float b = (newPixel >> 8) & 0xff;


                    image[x + 1][y] = image[x + 1][y] + quant_error * 7 / 16;
                    image[x - 1][y + 1] = image[x - 1][y + 1] + quant_error * 3 / 16;
                    image[x][y + 1] = image[x][y + 1] + quant_error * 5 / 16;
                    image[x + 1][y + 1] = image[x + 1][y + 1] + quant_error / 16;
            }
        }

        System.out.println("done");
        return image;
    }

    // pure black and white
    public static int[][] fixedThreshold(int[][] image) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[x].length; y++) {
                int currentColor = image[x][y];
                // return black or white
                image[x][y] = ((double) (getRed(currentColor) + getGreen(currentColor) + getBlue(currentColor)) /3 < 0.5 ? 0xffffffff : 0xff000000);
            }
        }

        return image;
    }
}
