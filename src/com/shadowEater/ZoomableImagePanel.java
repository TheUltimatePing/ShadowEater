package com.shadowEater;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ZoomableImagePanel extends JScrollPane {

    private final JLabel imageLabel = new JLabel();
    private int[][] source;
    private double zoom = 1.0;

    private static final double STEP = 0.1;
    private static final double MIN = 1.0;
    private static final double MAX = 10.0;

    public ZoomableImagePanel() {
        setViewportView(imageLabel);

        imageLabel.addMouseWheelListener(e -> {
            if (!e.isControlDown() || source == null) return;

            double oldZoom = zoom;
            zoom += (e.getWheelRotation() < 0 ? STEP : -STEP);
            zoom = Math.max(MIN, Math.min(MAX, zoom));

            if (zoom != oldZoom)
                updateImage(oldZoom, e.getPoint());
        });
    }

    public void setImage(int[][] image) {
        source = image;
        zoom = 1.0;
        render();
    }

    private void updateImage(double oldZoom, Point mouse) {
        JViewport vp = getViewport();
        Point pos = vp.getViewPosition();
        double scale = zoom / oldZoom;

        render();

        SwingUtilities.invokeLater(() ->
                vp.setViewPosition(new Point(
                        (int)((pos.x + mouse.x) * scale - mouse.x),
                        (int)((pos.y + mouse.y) * scale - mouse.y)
                ))
        );
    }

    private void render() {
        if (source == null) return;
        BufferedImage img = Converter.arrayToBufferedImage(source);

        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        int w = (int)(img.getWidth() * zoom);
        int h = (int)(img.getHeight() * zoom);

        Image scaled = img.getScaledInstance(w, h, Image.SCALE_FAST);
        imageLabel.setIcon(new ImageIcon(scaled));
        imageLabel.revalidate();
    }
}
