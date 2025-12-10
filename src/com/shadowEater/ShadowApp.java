package com.shadowEater;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;

import static com.shadowEater.ShadowFileInput.getFileChooser;

public class ShadowApp extends JFrame {
    private JPanel windowApp;
    private JButton convert_button;
    private JScrollPane can_scroll;
    private JLabel imageLabel;
    private JButton fileButton;
    private JButton downloadButton;
    private JCheckBox ditheringCheckBox;
    private JCheckBox blackAndWhiteCheckBox;
    private JTabbedPane tabbedPane1;
    private JButton selectColorsButton;
    private JButton selectFreeColorsButton;
    private JButton selectAllColorsButton;
    private JSpinner scaleSpinner;

    private static ShadowImage image;
    private static boolean[] choice = new boolean[64];
    {
        Arrays.fill(choice, true); // fill all the array with true (all the colors are selected by default)
    }

    public static boolean[] getChoice() {
        return choice;
    }

    public static void setChoice(boolean[] copy) {
        choice = copy;
    }

    public static void setChoice(int pos, boolean state) {
        // used to change the state of the user choices list
        choice[pos] = state;
    }

    // constructor
    public ShadowApp() {
        try {
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

            convert_button.addActionListener(_ -> {
                if (image != null) {
                    boolean dithering = ditheringCheckBox.isSelected();
                    boolean blackAndWhite = blackAndWhiteCheckBox.isSelected();

                    if (dithering && !blackAndWhite) {
                        image.setConvertedImage(Dithering.FloydSteinberg(image.getImage()));
                    } else if (!dithering && !blackAndWhite) {
                        image.setConvertedImage(Converter.convertImage(image.getImage()));
                    } else if (!dithering && blackAndWhite) {
                        image.setConvertedImage(Dithering.fixedThreshold(image.getImage()));
                    } else if (dithering && blackAndWhite) {
                        image.setConvertedImage(Dithering.FloydSteinberg(Dithering.fixedThreshold(image.getImage())));
                    }
                    // update what is shown
                    updateImagePreview(image.getConvertedImage());
                }

            });

            downloadButton.addActionListener(_ -> {
                // the converted image in a Buffered image
                BufferedImage imageFinal = Converter.arrayToBufferedImage(image.getImage());
                // create the file that will store the converted image
                File f = new File("Converted_Image.png");
                try {
                    ImageIO.write(imageFinal, "PNG", f);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            selectColorsButton.addActionListener(_ -> {
                // call the ShadowColors UI
                new ShadowColors();
            });

            selectFreeColorsButton.addActionListener(_ -> {
                WPlaceColor[] colors = WPlaceColor.values();
                for (int i = 0; i<colors.length; i++) {
                    setChoice(i, colors[i].getIsPaid());
                }
            });

            selectAllColorsButton.addActionListener(_ -> {
                int nbColor = WPlaceColor.values().length;
                for (int i = 0; i<nbColor; i++) {
                    setChoice(i, true);
                }
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // create the app icon and the name
        ImageIcon appIcon = new ImageIcon("src/image/logo.jpg");
        String titleName = "Shadow Eater converter";

        // TODO : add safer filepath
        // File fileSafe = new File("tmp"+File.separator+"abc.txt");
        // safer

        // what do the cross does
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // automatically set the page to full sized
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // set the icon to the app icon
        setIconImage(appIcon.getImage());
        // set the name of the window on windows
        setTitle(titleName);
        // set the name of the window on linux for some reason
        setName(titleName);
        // render the window
        setVisible(true);
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

        // we start the app
        SwingUtilities.invokeLater(() -> {
            ShadowApp app = new ShadowApp();
            // we bind the app to the main pannel
            app.setContentPane(app.windowApp);
        });
    }
}