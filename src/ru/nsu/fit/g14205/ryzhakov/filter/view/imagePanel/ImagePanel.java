package ru.nsu.fit.g14205.ryzhakov.filter.view.imagePanel;

import ru.nsu.fit.g14205.ryzhakov.filter.model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private static final Color BORDER_COLOR = Color.black;
    private BufferedImage imageBuffer;
    private ImageModel imageModel;
    int firstImageWidth;
    int firstImageHeight;
    Graphics2D g2d;

    public ImagePanel(ImagePanelClickListener imagePanelClickListener, ImageModel model) {
        if (null == imagePanelClickListener) {
            return;
        }
        imageModel = model;

        int imageHeight = 400;
        int imageWidth =  1300;


        imageBuffer = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = imageBuffer.createGraphics();


        g2d.setColor(BORDER_COLOR);
        float [] dash = {10};
        g2d.setStroke(new BasicStroke(2,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER,
                1, dash, 0.0f));
        g2d.drawLine(10, 10, 10, 360);
        g2d.drawLine(10, 10, 360, 10);
        g2d.drawLine(360, 360, 10, 360);
        g2d.drawLine(360, 360, 360, 10);

        g2d.drawLine(370, 10, 370, 360);
        g2d.drawLine(370, 10, 720, 10);
        g2d.drawLine(720, 360, 370, 360);
        g2d.drawLine(720, 360, 720, 10);

        g2d.drawLine(730, 10, 730, 360);
        g2d.drawLine(730, 10, 1080, 10);
        g2d.drawLine(1080, 360, 730, 360);
        g2d.drawLine(1080, 360, 1080, 10);

        setPreferredSize(new Dimension(imageWidth, imageHeight));

        MouseAdapter mouseAdapter = new MouseAdapter() {
//            float width = firstImageWidth / imageModel.getFirstImage().getWidth() * 350;
//            float height = firstImageHeight / imageModel.getFirstImage().getHeight() * 350;

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
//                if (e.getX() > 10 && e.getX() < 360 && e.getY() > 10 && e.getY() < 360){
//                    if
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseDragged(e);
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int imageHeight = 400;
        int imageWidth =  1300;

        if (imageModel.getFirstImage() != null) {
            float width = imageModel.getFirstImage().getWidth();
            float height = imageModel.getFirstImage().getHeight();
            if (width > height){
                height = height / width * 350;
                width = 350;
            } else {
                width = width / height * 350;
                height = 350;
            }

            firstImageWidth = (int)width;
            firstImageHeight = (int)height;
            g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, this);
        }
        g.drawImage(imageBuffer, 0, 0, imageWidth, imageHeight, this);
    }

}
