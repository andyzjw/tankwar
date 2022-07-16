package com.tankwar;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.List;

public abstract class Tank extends GameObject {
    public int width = 40;
    public int height = 34;
    private int speed = 3;
    public Direction direction = Direction.UP;

    private String upImg;
    private String leftImg;
    private String rightImg;
    private String downImg;

    // 攻击冷却状态
    private boolean attackCollDown = true;
    private int attackCollDownTime = 1000;


    public Tank(String img, int x, int y, GamePanel gamePanel,
                String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel);
        this.upImg = upImg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.downImg = downImg;
    }

    public void leftward() {
        setImage(leftImg);
        direction = Direction.LEFT;
        if (!hitWall(x - speed, y) && !moveToBorder(x - speed, y)) {
            x -= speed;
        }
    }

    public void upward() {
        setImage(upImg);
        direction = Direction.UP;
        if (!hitWall(x, y - speed) && !moveToBorder(x, y - speed)) {
            y -= speed;
        }


    }

    public void rightward() {
        setImage(rightImg);
        direction = Direction.RIGHT;
        if (!hitWall(x + speed, y) && !moveToBorder(x + speed, y)) {
            x += speed;
        }
    }

    public void downward() {
        setImage(downImg);
        direction = Direction.DOWN;
        if (!hitWall(x, y + speed) && !moveToBorder(x, y + speed)) {
            y += speed;

        }
    }

    public boolean hitWall(int x, int y) {
        List<Wall> walls = this.gamePanel.wallList;
        Rectangle next = new Rectangle(x, y, width, height);
        for (Wall wall : walls) {
            if (next.intersects(wall.getRectangle())) {
                return true;
            }
        }

        return false;
    }

    public boolean moveToBorder(int x, int y) {
        if (x < 0) {
            return true;
        } else if (x + width > this.gamePanel.getWidth()) {
            return true;
        } else if (y < 0) {
            return true;
        } else if (y + height > this.gamePanel.getHeight()) {
            return true;
        }
        return false;
    }

    public void attack() {
        if (attackCollDown) {
            Point p = this.getHeadPoint();

            Bullet bullet = new Bullet("image/5.png", p.x, p.y, this.gamePanel, this.direction);
            this.gamePanel.bulletList.add(bullet);
            new AttackCD().start();
        }

    }

    class AttackCD extends Thread {
        @Override
        public void run() {
            // 将攻击功能设置为冷却时间
            attackCollDown = false;
            // 休眠1秒
            try {
                Thread.sleep(attackCollDownTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            attackCollDown = true;
            this.stop();

        }
    }

    public Point getHeadPoint() {
        switch (direction) {
            case LEFT:
                return new Point(x, y + height / 2);
            case RIGHT:
                return new Point(x + width, y + height / 2);
            case UP:
                return new Point(x + width / 2, y);
            case DOWN:
                return new Point(x + width / 2, y + height);
            default:
                return null;
        }
    }


    public void setImage(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    @Override
    public abstract void paintSelft(Graphics graphics);

    @Override
    public abstract Rectangle getRectangle();

}
