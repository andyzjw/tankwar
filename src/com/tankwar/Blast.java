package com.tankwar;

import java.awt.*;

public class Blast extends GameObject {
    static Image[] images = new Image[16];
    int explodeCount = 0;
    static {
        for (int i = 0 ; i< 16;i++){
            images[i] = Toolkit.getDefaultToolkit().getImage("image/images/z"+(i+1)+".gif");
        }
    }

    public Blast(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelft(Graphics graphics) {
        if (explodeCount < 16){
            graphics.drawImage(images[explodeCount], x,y,null);
            explodeCount++;
        }

        graphics.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}
