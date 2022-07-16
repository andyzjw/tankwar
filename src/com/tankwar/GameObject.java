package com.tankwar;

import javax.tools.Tool;
import java.awt.*;

public abstract class GameObject {
    public Image img;
    // 横坐标
    public int x;
    // 纵坐标
    public int y;

    public GamePanel gamePanel;

    public GameObject() {
    }

    public GameObject(String img, int x, int y, GamePanel gamePanel) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public abstract void paintSelft(Graphics graphics);

    public abstract Rectangle getRectangle();
}
