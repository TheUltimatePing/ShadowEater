package com.shadowEater;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;

import static com.shadowEater.ShadowFileInput.getFileChooser;

public class ShadowApp {
    private JPanel windowApp;
    private JButton convert_button;
    private JScrollPane can_scroll;
    private JLabel imageLabel;
    private JButton fileButton;
    private JButton downloadButton;

    private static ShadowImage image;
    private static boolean[] choice = new boolean[64];
    {
        Arrays.fill(choice, true); // fill all the array with true (all the colors are selected by default)
    }

    public static boolean[] getChoice() {
        return choice;
    }

    public ShadowApp() {
        /*
        convert_button.addActionListener(_ -> {
            image.setConvertedImage(Converter.convertImage(image.getImage()));
            updateImagePreview(image.getConvertedImageArray());
        });

         */

        /*
        fileButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg");
            int exist = chooser.showOpenDialog(null);
            if (exist == JFileChooser.APPROVE_OPTION) {
                image = new ShadowImage(chooser.getSelectedFile());
                updateImagePreview();
            }
        });
        */
        /*
        fileButton.addActionListener(_ -> {
            String rootDir = "null"; // null for home
            String filesDesc = "Image files";
            JFileChooser jfc = getFileChooser(rootDir, filesDesc, "jpg", "png");

            int result = jfc.showOpenDialog(null); // ou un parent component à la place de null
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                image = new ShadowImage(selectedFile);
                updateImagePreview(image.getImage());
            }
        });

         */
        /*
        fileButton.addActionListener(_-> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(parent);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getName());
            }
        });

         */
        /*
        fileButton.addActionListener(_ -> {
            FileDialog chooser = new FileDialog((Frame) null, "Image", FileDialog.LOAD);
            chooser.setFilenameFilter((dir, name) ->
                    name.endsWith(".png") || name.endsWith(".jpg"));
            chooser.setVisible(true);

            String fileName = chooser.getFile();
            String dirName = chooser.getDirectory();

            if (fileName != null && dirName != null) {
                File selectedFile = new File(dirName, fileName);
                image = new ShadowImage(selectedFile);
                updateImagePreview(image.getImage());
            }
        });

         */
    }

    private void updateImagePreview(int[][] toRender) {
        if (image == null) return;
        BufferedImage imageFinal = Converter.arrayToBufferedImage(toRender);
        imageLabel.setIcon(new ImageIcon(imageFinal));
    }

    public static void main(String[] args) {
        // set the style to the default system one
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // create the app icon and the name
        ImageIcon appIcon = new ImageIcon("image/reginleif.jpg");
        String titleName = "Shadow Eater";

        // create the window for the app
        JFrame mainWindow = new JFrame(titleName);

        mainWindow.setContentPane(new ShadowApp().windowApp);
        // what do the cross does
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // automatically set the page to full sized
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // set the icon to the app icon
        mainWindow.setIconImage(appIcon.getImage());
        // render the window
        mainWindow.setVisible(true);
    }
}