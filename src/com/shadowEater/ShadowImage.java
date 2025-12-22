package com.shadowEater;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShadowImage {
    public ShadowImage(File filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(filePath);
        } catch (IOException e) {
            System.out.println(System.getProperty("user.dir"));
        }
        // convert the image to an array
        assert image != null;
        this.imageArray = Converter.bufferedToArray(image);
    }

    // not transformed image
    private int[][] imageArray = {};

    // transformed image
    private int[][] convertedImageArray = {};

    public int[][] getImage() {
        return imageArray;
    }

    public int[][] getConvertedImage()  {
        return convertedImageArray;
    }

    public void setConvertedImage(int[][] convertedImage) {
        convertedImageArray = convertedImage;
    }
}
