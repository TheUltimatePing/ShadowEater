import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

class Converter {
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
    public static int[][] transformImage(int[][] image) {
        for (int i = 0; i<image.length; i++) {
            for (int j = 0; j<image[i].length; j++) {
                closestColor(image[i][j])
            }
        }
    }

    // for a pixel give the closest color in the list enabled by the user if at least one is enabled
    private static Color closestColor(Color colorInput) {
        Color closest = new Color(0, 0, 0);

        return closest;
    }

}
