package com.shadowEater;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class ShadowApp {
    private JPanel windowApp;
    private JButton convert_button;
    private JScrollPane can_scroll;
    private JLabel imageLabel;
    private JButton fileButton;
    private JButton downloadButton;

    private static ShadowImage image;

    public ShadowApp() {
        convert_button.addActionListener(e -> JOptionPane.showInputDialog("Select an image"));

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
        fileButton.addActionListener(e -> {
            FileDialog chooser = new FileDialog((Frame) null, "Image", FileDialog.LOAD);
            chooser.setFilenameFilter((dir, name) ->
                    name.endsWith(".png") || name.endsWith(".jpg"));
            chooser.setVisible(true);

            String fileName = chooser.getFile();
            String dirName = chooser.getDirectory();

            if (fileName != null && dirName != null) {
                File selectedFile = new File(dirName, fileName);
                image = new ShadowImage(selectedFile);
                updateImagePreview();
            }
        });
    }

    private void updateImagePreview() {
        if (image == null) return;
        BufferedImage imageFinal = Converter.arrayToBufferedImage(image.getImage());
        imageLabel.setIcon(new ImageIcon(imageFinal));
    }

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

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
        // render the window
        mainWindow.setVisible(true);
    }
}
