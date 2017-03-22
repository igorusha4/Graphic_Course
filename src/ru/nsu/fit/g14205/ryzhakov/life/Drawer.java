package ru.nsu.fit.g14205.ryzhakov.life;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Drawer {
    private Graphics2D graphics2D;
    private BufferedImage image;

    private Color currentColor = Color.BLACK;
    public static final int FONT_SIZE = 12;
    private int lineWidth = 1;

    public Drawer(Graphics2D graphics2D, BufferedImage image) {
        this.graphics2D = graphics2D;
        this.image = image;

        graphics2D.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
    }

    public void setLineWidth(int width){
        lineWidth = width;

        graphics2D.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    private void drawMyLine(int x1, int y1, int x2, int y2){
        boolean is_vertical_line = abs(y2 - y1) > abs(x2 - x1);

        if (is_vertical_line){
            int temp = x1;
            x1 = y1;
            y1 = temp;

            temp = x2;
            x2 = y2;
            y2 = temp;
        }

        if (x1 > x2){
            int temp = x1;
            x1 = x2;
            x2 = temp;

            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        int dx = abs(x2 - x1);
        int dy = abs(y2 - y1);

        int error = dx / 2;

        int yStep = (y1 < y2) ? 1 : -1;

        int y = y1;
        for (int x = min(x1, x2); x <= max(x1,x2); ++x)
        {
            image.setRGB((is_vertical_line ? y : x), (is_vertical_line ? x : y), currentColor.getRGB());

            error -= dy;
            if (error < 0)
            {
                y += yStep;


                error += dx;
            }
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2){
        if(lineWidth != 1){
            graphics2D.drawLine(x1, y1, x2, y2);
            return;
        }

        drawMyLine(x1, y1, x2, y2);
    }

    public void setCurrentColor(Color currentColor){
        this.currentColor = currentColor;
        graphics2D.setColor(currentColor);
    }

    private class Span{
        int y = 0;
        int x1 = 0;
        int x2 = 0;

        Span(int y, int x1, int x2) {
            this.y = y;
            this.x1 = min(x1, x2);
            this.x2 = max(x1, x2);
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof Span)){
                return false;
            }

            Span span = (Span)o;
            return span.y == y && span.x1 == x1 && span.x2 == x2;
        }
    }

    private Span findSpan(int x, int y, int color){
        int left = x;
        int right = x;

        while(left - 1 >= 0 && image.getRGB(left - 1, y) == color){
            --left;
        }

        while(right + 1 < image.getWidth() && image.getRGB(right + 1, y) == color){
            ++right;
        }

        return new Span(y, left, right);
    }

    private void paintSpan(Span span){
        drawMyLine(span.x1, span.y, span.x2, span.y);
    }

    private void runOnSpan(Stack<Span> spans, Span currentSpan, int oldColor, int direction){
        if(currentSpan.y - direction > 0 && currentSpan.y - direction < image.getHeight()) {
            for (int ix = currentSpan.x1; ix <= currentSpan.x2; ++ix) {
                if (image.getRGB(ix, currentSpan.y - direction) == oldColor) {
                    Span newSpan = findSpan(ix, currentSpan.y - direction, oldColor);

                    spans.push(newSpan);

                    ix = newSpan.x2;
                }
            }
        }
    }

    public void fillArea(int x, int y){
        int oldColor = image.getRGB(x, y);

        Stack<Span> spans = new Stack<>();

        Span currentSpan = findSpan(x, y, oldColor);
        spans.push(currentSpan);

        while (!spans.empty()){
            currentSpan = spans.pop();
            paintSpan(currentSpan);
            runOnSpan(spans, currentSpan, oldColor, -1);
            runOnSpan(spans, currentSpan, oldColor, 1);
        }
    }

    public void drawText(String text, int x, int y){
        graphics2D.drawString(text, x, y);
    }
}
