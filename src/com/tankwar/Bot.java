package com.tankwar;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.Random;

public class Bot extends Tank {
    int moveTime;

    public Bot(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
    }


    public Direction getRandomDirection() {
        Random random = new Random();
        int rnum = random.nextInt(4);
        switch (rnum) {
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return null;
        }
    }

    public void go() {
        attack();
        if (moveTime > 20) {
            direction = getRandomDirection();
            moveTime = 0;
        } else {
            moveTime++;
        }

        switch (direction) {
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
        }
    }
    @Override
    public void attack(){
        Point p = getHeadPoint();
        Random random = new Random();
        int num = random.nextInt(400);

        if (num < 4){
            this.gamePanel.bulletList.add(new EnemyBullet("image/enemy/5.png", p.x, p.y, this.gamePanel, this.direction));
        }
    }



    @Override
    public void paintSelft(Graphics graphics) {
        graphics.drawImage(img, x, y, null);
        go();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
