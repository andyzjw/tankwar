package com.tankwar;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends GameObject {

    public int width = 10;
    public int height = 10;
    public int speed = 7;

    Direction direction;

    public Bullet(String img, int x, int y,
                  GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel);

        this.direction = direction;
    }

    public void leftward() {
        x -= speed;

    }

    public void upward() {
        y -= speed;

    }

    public void rightward() {
        x += speed;

    }

    public void downward() {
        y += speed;
    }

    public void go() {
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
        this.hitWall();
        this.moveToBorder();
        this.hitBase();
    }

    public void hitBot() {
        List<Bot> bots = this.gamePanel.botList;
        for (Bot bot : bots) {
            if (this.getRectangle().intersects(bot.getRectangle())) {
                this.gamePanel.botList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void hitBase() {
        List<Base> bots = this.gamePanel.baseList;
        for (Base bot : bots) {
            if (this.getRectangle().intersects(bot.getRectangle())) {
                this.gamePanel.baseList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void moveToBorder(){
        if (x < 0 || x+width > this.gamePanel.getWidth()){
            this.gamePanel.removeList.add(this);
        }
        if (y < 0 || y + height > this.gamePanel.getHeight()){
            this.gamePanel.removeList.add(this);
        }

    }
     public void hitWall(){
        List<Wall> walls =  this.gamePanel.wallList;
         for (Wall wall: walls) {
             if (this.getRectangle().intersects(wall.getRectangle())){
                 this.gamePanel.wallList.remove(wall);
                 System.out.println(wall);
                 this.gamePanel.removeList.add(this);
                 break;
             }
         }
     }
    @Override
    public void paintSelft(Graphics graphics) {
        graphics.drawImage(img, x, y, null);
        this.go();
        this.hitBot();

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
