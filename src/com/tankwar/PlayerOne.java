package com.tankwar;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerOne extends Tank {
    public PlayerOne(String img, int x, int y, GamePanel gamePanel,
                     String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
    }

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                attack();
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
        }
    }

    public void move() {
        if (left) {
            leftward();

        } else if (right) {
            rightward();

        } else if (up) {
            upward();
        } else if (down) {
            downward();
        }

    }


    @Override
    public void paintSelft(Graphics graphics) {
        graphics.drawImage(img, x, y, null);
        move();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }


}
