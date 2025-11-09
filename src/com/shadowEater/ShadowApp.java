package com.shadowEater;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShadowApp {
    private JPanel windowApp;
    private JButton convert_button;

    public ShadowApp() {
        convert_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("Select an image");
            }
        });
    }

    public static void main(String[] args) throws IOException {
        ImageIcon appIcon = new ImageIcon("image/reginleif.jpg");
        String titleName = "Shadow Eater";

        JFrame mainWindow = new JFrame(titleName);
        mainWindow.setContentPane(new ShadowApp().windowApp);
        // what do the cross does
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // automatically set the page to full sized
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // set the icon to the app icon
        mainWindow.setIconImage(appIcon.getImage());

        // create the image to process
        BufferedImage image = ImageIO.read(new File("image/reginleif.jpg"));
        // create the array for the image
        int[][] imageArray = {};
        // if the image is not null we convert is to an array
        if (image != null) {
            imageArray = Converter.bufferedToArray(image);
        }

        // transform an array to a render able image
        BufferedImage img = Converter.arrayToBufferedImage(imageArray);
        // put the image in a label
        JLabel convertedImage = new JLabel(new ImageIcon(img));

        // add the different element
        mainWindow.add(convertedImage);

        // render the window
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
