package com.shadowEater;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.shadowEater.ShadowFileInput.getFileChooser;

public class ShadowAppGUI extends JPanel {
    private JButton convertButton, fileButton, downloadButton, selectColorButton, selectFreeColorsButton, selectAllColorsButton; // used on the file option tab
    private JLabel imageLabel;
    private JCheckBox ditheringCheckBox, blackAndWhiteCheckBox;
    private JScrollPane canScroll;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JSpinner scaleSpinner;

    // the constructor
    ShadowAppGUI() {
        this.setLayout(new GridLayout(2, 1));

        setTabbedPane();
        setImageArea();
    }

    /* code for the parameters button */
    private void setTabbedPane() {
        tabbedPane.addTab("File options", setFilePanel());
        tabbedPane.addTab("Parameters", setParameter());
        tabbedPane.addTab("Colors options", setColors());

        this.add(tabbedPane);
    }

    private JPanel setFilePanel() {
        JPanel filePanel = new JPanel();

        filePanel.setLayout(new FlowLayout());

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
        ditheringCheckBox = new JCheckBox("Dithering");
        return ditheringCheckBox;
    }

    private JCheckBox setBlackAndWHiteCheckbox() {
        blackAndWhiteCheckBox = new JCheckBox("Black and White");
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
        selectColorButton = new JButton("Select colors");

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

        return selectAllColorsButton;
    }

    /* code for the image area */
    private void setImageArea() {
        // TODO : make the scrollPane work
        // canScroll = new JScrollPane();
        imageLabel = new JLabel();

        JViewport imageView = new JViewport();
        imageView.setView(imageLabel);

        // canScroll.setViewport(imageView);

        imageLabel.setIcon(new ImageIcon("src/image/logo.jpg"));

        // this.add(canScroll);
        this.add(imageLabel);
        //canScroll.add(imageLabel);
    }

    public void updateImagePreview(int[][] toRender) {
        if (ShadowApp.image == null) return;
        BufferedImage imageFinal = Converter.arrayToBufferedImage(toRender);
        imageLabel.setIcon(new ImageIcon(imageFinal));
    }

    public JPanel getGUI() {
        return this;
    }
}
