package com.shadowEater;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShadowImage {
    public ShadowImage(File filePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // convert the image to an array
        this.imageArray = Converter.bufferedToArray(image);
    }

    private int[][] imageArray = {};

    public int[][] getImage() {
        return imageArray;
    }
}
