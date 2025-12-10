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

    // not transformed image
    private int[][] imageArray = {};
    // transformed image
    private int[][] convertedImageArray = {};

    // the scale fof the image at the begining
    private int scale = 0;

    public int[][] getImage() {
        return imageArray;
    }

    public int[][] getConvertedImage()  {
        return convertedImageArray;
    }

    public void setConvertedImage(int[][] convertedImage) {
        convertedImageArray = convertedImage;
    }

    public void setScale(int n) {
        scale = n;
    }
}
