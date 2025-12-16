package com.shadowEater;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.shadowEater.ShadowFileInput.getFileChooser;

public class ShadowAppGUI extends JFrame {
    private JButton convertButton, fileButton, downloadButton, selectColorButton, selectFreeColorsButton, selectAllColorsButton; // used on the file option tab
    private JLabel imageLabel;
    private JCheckBox ditheringCheckBox, blackAndWhiteCheckBox;
    private JScrollPane canScroll;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JSpinner scaleSpinner;

    private JPanel windowApp;

    // the constructor
    ShadowAppGUI() {
        // create the app icon and the name
        ImageIcon appIcon = new ImageIcon("src/image/logo.jpg");
        String titleName = "Shadow Eater converter";

        this.add(setPannel());

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



    private JPanel setPannel() {
        JPanel windowApp = new JPanel();
        windowApp.setLayout(new GridLayout(1, 2));

        setTabbedPane();
        setImageArea();

        return windowApp;
    }

    /* code for the parameters button */
    private void setTabbedPane() {
        tabbedPane.add(setFilePanel());
        tabbedPane.add(setParameter());
        tabbedPane.add(setColors());

        windowApp.add(tabbedPane);
    }

    private JPanel setFilePanel() {
        JPanel filePanel = new JPanel();
        filePanel.add(setFileButton());
        filePanel.add(setConvertButton());
        filePanel.add(setDownloadButton());

        return filePanel;
    }

    private JButton setFileButton() {
        fileButton = new JButton("Select file");

        fileButton.addActionListener(_ -> {
            String rootDir = "null"; // null for home
            String filesDesc = "Image files";
            JFileChooser jfc = getFileChooser(rootDir, filesDesc, "jpg", "png");

            int result = jfc.showOpenDialog(null); // ou un parent component à la place de null
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                ShadowApp.image = new ShadowImage(selectedFile);
                updateImagePreview(ShadowApp.image.getImage());
            }
        });

        return fileButton;
    }

    private JButton setConvertButton() {
        convertButton = new JButton("Convert");

        convertButton.addActionListener(_ -> {
            if (ShadowApp.image != null) {
                boolean dithering = ditheringCheckBox.isSelected();
                boolean blackAndWhite = blackAndWhiteCheckBox.isSelected();

                if (dithering && !blackAndWhite) {
                    ShadowApp.image.setConvertedImage(Dithering.FloydSteinberg(ShadowApp.image.getImage()));
                } else if (!dithering && !blackAndWhite) {
                    ShadowApp.image.setConvertedImage(Converter.convertImage(ShadowApp.image.getImage()));
                } else if (!dithering && blackAndWhite) {
                    ShadowApp.image.setConvertedImage(Dithering.fixedThreshold(ShadowApp.image.getImage()));
                } else if (dithering && blackAndWhite) {
                    ShadowApp.image.setConvertedImage(Dithering.FloydSteinberg(Dithering.fixedThreshold(ShadowApp.image.getImage())));
                }
                // update what is shown
                updateImagePreview(ShadowApp.image.getConvertedImage());
            }

        });

        return convertButton;
    }

    private JButton setDownloadButton() {
        downloadButton = new JButton("Download button");

        downloadButton.addActionListener(_ -> {
            // the converted image in a Buffered image
            BufferedImage imageFinal = Converter.arrayToBufferedImage(ShadowApp.image.getImage());
            // create the file that will store the converted image
            File f = new File("Converted_Image.png");
            try {
                ImageIO.write(imageFinal, "PNG", f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return downloadButton;
    }

    private JPanel setParameter() {
        JPanel paraPanel = new JPanel();
        paraPanel.add(setDitheringCheckbox());
        paraPanel.add(setBlackAndWHiteCheckbox());
        paraPanel.add(setScaleSpinner());

        return paraPanel;
    }

    private JCheckBox setDitheringCheckbox() {
        ditheringCheckBox = new JCheckBox();
        return ditheringCheckBox;
    }

    private JCheckBox setBlackAndWHiteCheckbox() {
        blackAndWhiteCheckBox = new JCheckBox();
        return blackAndWhiteCheckBox;
    }

    private JSpinner setScaleSpinner() {
        scaleSpinner = new JSpinner();
        scaleSpinner.addChangeListener(e -> ShadowApp.image.setScale((int) (scaleSpinner.getValue())));

        return scaleSpinner;
    }

    private JPanel setColors() {
        JPanel colorPanel = new JPanel();
        colorPanel.add(setSelectColorButton());
        colorPanel.add(setSelectFreeColorButton());
        colorPanel.add(setSelectAllColorButton());

        return colorPanel;
    }

    private JButton setSelectColorButton() {
        selectColorButton = new JButton();

        selectColorButton.addActionListener(_ -> {
            // call the ShadowColors UI
            new ShadowColors();
        });

        return selectColorButton;
    }

    private JButton setSelectFreeColorButton() {
        selectFreeColorsButton = new JButton("Free");

        selectFreeColorsButton.addActionListener(_ -> {
            WPlaceColor[] colors = WPlaceColor.values();
            for (int i = 0; i<colors.length; i++) {
                ShadowApp.setChoice(i, colors[i].getIsPaid());
            }
        });

        return selectFreeColorsButton;
    }

    private JButton setSelectAllColorButton() {
        selectAllColorsButton = new JButton("All");

        selectAllColorsButton.addActionListener(_ -> {
            int nbColor = WPlaceColor.values().length;
            for (int i = 0; i<nbColor; i++) {
                ShadowApp.setChoice(i, true);
            }
        });

        return setSelectAllColorButton();
    }

    /* code for the image area */
    private void setImageArea() {
        windowApp.add(canScroll);
        canScroll.add(imageLabel);
    }

    public void updateImagePreview(int[][] toRender) {
        if (ShadowApp.image == null) return;
        BufferedImage imageFinal = Converter.arrayToBufferedImage(toRender);
        imageLabel.setIcon(new ImageIcon(imageFinal));
    }

    public JFrame getGUI() {
        return this;
    }
}
