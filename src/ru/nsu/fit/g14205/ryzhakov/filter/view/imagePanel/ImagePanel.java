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
        float [] dash = {5};
        g2d.setStroke(new BasicStroke(2,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER,
                1, dash, 0.0f));
        g2d.drawLine(10 - 1, 10 - 1, 10 - 1, 360 + 1);
        g2d.drawLine(10 - 1, 10 - 1, 360 + 1, 10 - 1);
        g2d.drawLine(360 + 1, 360 + 1, 10 - 1, 360 + 1);
        g2d.drawLine(360 + 1, 360 + 1, 360 + 1, 10 - 1);

        g2d.drawLine(370 - 1, 10 - 1, 370 - 1, 360 + 1);
        g2d.drawLine(370 - 1, 10 - 1, 720 + 1, 10 - 1);
        g2d.drawLine(720 + 1, 360 + 1, 370 - 1, 360 + 1);
        g2d.drawLine(720 + 1, 360 + 1, 720 + 1, 10 - 1);

        g2d.drawLine(730 - 1, 10 - 1, 730 - 1, 360 + 1);
        g2d.drawLine(730 - 1, 10 - 1, 1080 + 1, 10 - 1);
        g2d.drawLine(1080 + 1, 360 + 1, 730 - 1, 360 + 1);
        g2d.drawLine(1080 + 1, 360 + 1, 1080 + 1, 10 - 1);

        setPreferredSize(new Dimension(imageWidth, imageHeight));

    }

    public void loadImage() {
        float width = imageModel.getFirstImage().getWidth();
        float height = imageModel.getFirstImage().getHeight();
        if (width > height) {
            height = height / width * 350;
            width = 350;
        } else {
            width = width / height * 350;
            height = 350;
        }

        firstImageWidth = (int) width;
        firstImageHeight = (int) height;
        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, this);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            float width = (float) firstImageWidth / imageModel.getFirstImage().getWidth() * 350;
            float height = (float) firstImageHeight / imageModel.getFirstImage().getHeight() * 350;

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                mouseDragged(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int mouseX = e.getX();
                int mouseY = e.getY();

                    if (mouseX > width / 2 + 10 && mouseX < firstImageWidth - width / 2 + 10 && mouseY > height / 2 + 10 && mouseY < firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (mouseX - width / 2), (int) (mouseY - height / 2), (int) (mouseX - width / 2), (int) (mouseY + height / 2));
                        g2d.drawLine((int) (mouseX - width / 2), (int) (mouseY - height / 2), (int) (mouseX + width / 2), (int) (mouseY - height / 2));
                        g2d.drawLine((int) (mouseX + width / 2), (int) (mouseY + height / 2), (int) (mouseX - width / 2), (int) (mouseY + height / 2));
                        g2d.drawLine((int) (mouseX + width / 2), (int) (mouseY + height / 2), (int) (mouseX + width / 2), (int) (mouseY - height / 2));

                        repaint();
                    }
                    else if (mouseX < width / 2 + 10 && mouseX < firstImageWidth - width / 2 + 10 && mouseY > height / 2 + 10 && mouseY < firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (10), (int) (mouseY - height / 2), (int) (10), (int) (mouseY + height / 2));
                        g2d.drawLine((int) (10), (int) (mouseY - height / 2), (int) (width + 10), (int) (mouseY - height / 2));
                        g2d.drawLine((int) (width + 10), (int) (mouseY + height / 2), (int) (10), (int) (mouseY + height / 2));
                        g2d.drawLine((int) (width + 10), (int) (mouseY + height / 2), (int) (width + 10), (int) (mouseY - height / 2));
                        repaint();
                    }
                    else if (mouseX < width / 2 + 10 && mouseX < firstImageWidth - width / 2 + 10 && mouseY < height / 2 + 10 && mouseY < firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (10), (int) (10), (int) (10), (int) (height + 10));
                        g2d.drawLine((int) (10), (int) (10), (int) (width + 10), (int) (10));
                        g2d.drawLine((int) (width + 10), (int) (height + 10), (int) (10), (int) (height + 10));
                        g2d.drawLine((int) (width + 10), (int) (height + 10), (int) (width + 10), (int) (10));
                        repaint();
                    }
                    else if (mouseX > width / 2 + 10 && mouseX < firstImageWidth - width / 2 + 10 && mouseY < height / 2 + 10 && mouseY < firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (mouseX - width / 2), (int) (10), (int) (mouseX - width / 2), (int) (height + 10));
                        g2d.drawLine((int) (mouseX - width / 2), (int) (10), (int) (mouseX + width / 2), (int) (10));
                        g2d.drawLine((int) (mouseX + width / 2), (int) (height + 10), (int) (mouseX - width / 2), (int) (height + 10));
                        g2d.drawLine((int) (mouseX + width / 2), (int) (height + 10), (int) (mouseX + width / 2), (int) (10));
                        repaint();
                    }
                    else if (mouseX > width / 2 + 10 && mouseX > firstImageWidth - width / 2 + 10 && mouseY < height / 2 + 10 && mouseY < firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (firstImageWidth + 10), (int) (10), (int) (firstImageWidth + 10), (int) (height + 10));
                        g2d.drawLine((int) (firstImageWidth + 10), (int) (10), (int) (firstImageWidth - width + 10), (int) (10));
                        g2d.drawLine((int) (firstImageWidth - width + 10), (int) (height + 10), (int) (firstImageWidth + 10), (int) (height + 10));
                        g2d.drawLine((int) (firstImageWidth - width + 10), (int) (height + 10), (int) (firstImageWidth - width + 10), (int) (10));
                        repaint();
                    }
                    else if (mouseX > width / 2 + 10 && mouseX > firstImageWidth - width / 2 + 10 && mouseY > height / 2 + 10 && mouseY < firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (firstImageWidth + 10), (int) (mouseY - height / 2), (int) (firstImageWidth + 10), (int) (mouseY + height / 2));
                        g2d.drawLine((int) (firstImageWidth + 10), (int) (mouseY - height / 2), (int) (firstImageWidth - width + 10), (int) (mouseY - height / 2));
                        g2d.drawLine((int) (firstImageWidth - width + 10), (int) (mouseY + height / 2), (int) (firstImageWidth + 10), (int) (mouseY + height / 2));
                        g2d.drawLine((int) (firstImageWidth - width + 10), (int) (mouseY + height / 2), (int) (firstImageWidth - width + 10), (int) (mouseY - height / 2));
                        repaint();
                    }
                    else if (mouseX > width / 2 + 10 && mouseX > firstImageWidth - width / 2 + 10 && mouseY > height / 2 + 10 && mouseY > firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (firstImageWidth + 10), (int) (firstImageHeight + 10), (int) (firstImageWidth + 10), (int) (firstImageHeight - height + 10));
                        g2d.drawLine((int) (firstImageWidth + 10), (int) (firstImageHeight + 10), (int) (firstImageWidth - width + 10), (int) (firstImageHeight + 10));
                        g2d.drawLine((int) (firstImageWidth - width + 10), (int) (firstImageHeight - height + 10), (int) (firstImageWidth + 10), (int) (firstImageHeight - height + 10));
                        g2d.drawLine((int) (firstImageWidth - width + 10), (int) (firstImageHeight - height + 10), (int) (firstImageWidth - width + 10), (int) (firstImageHeight + 10));
                        repaint();
                    }
                    else if (mouseX > width / 2 + 10 && mouseX < firstImageWidth - width / 2 + 10 && mouseY > height / 2 + 10 && mouseY > firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (mouseX - width / 2), (int) (firstImageHeight + 10), (int) (mouseX - width / 2), (int) (firstImageHeight - height + 10));
                        g2d.drawLine((int) (mouseX - width / 2), (int) (firstImageHeight + 10), (int) (mouseX + width / 2), (int) (firstImageHeight + 10));
                        g2d.drawLine((int) (mouseX + width / 2), (int) (firstImageHeight - height + 10), (int) (mouseX - width / 2), (int) (firstImageHeight - height + 10));
                        g2d.drawLine((int) (mouseX + width / 2), (int) (firstImageHeight - height + 10), (int) (mouseX + width / 2), (int) (firstImageHeight + 10));
                        repaint();
                    }
                    else if (mouseX < width / 2 + 10 && mouseX < firstImageWidth - width / 2 + 10 && mouseY > height / 2 + 10 && mouseY > firstImageHeight - height / 2 + 10) {
                        g2d.drawImage(imageModel.getFirstImage(), 10, 10, firstImageWidth, firstImageHeight, e.getComponent());
                        g2d.drawLine((int) (10), (int) (firstImageHeight + 10), (int) (10), (int) (firstImageHeight - height + 10));
                        g2d.drawLine((int) (10), (int) (firstImageHeight + 10), (int) (width + 10), (int) (firstImageHeight + 10));
                        g2d.drawLine((int) (width + 10), (int) (firstImageHeight - height + 10), (int) (10), (int) (firstImageHeight - height + 10));
                        g2d.drawLine((int) (width + 10), (int) (firstImageHeight - height + 10), (int) (width + 10), (int) (firstImageHeight + 10));
                        repaint();
                    }



            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (mouseX > 10 && mouseX < firstImageWidth && mouseY > 10 && mouseY < firstImageHeight) {
                    mouseDragged(e);
                }
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


        g.drawImage(imageBuffer, 0, 0, imageWidth, imageHeight, this);
    }

}
