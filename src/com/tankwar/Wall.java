package com.tankwar;

import java.awt.*;

public class Wall extends GameObject {
    int length = 50;

    public Wall(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelft(Graphics graphics) {
        graphics.drawImage(img, x, y, gamePanel);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, length, length);
    }
}
