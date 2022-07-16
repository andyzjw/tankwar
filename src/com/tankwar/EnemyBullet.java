package com.tankwar;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.List;

public class EnemyBullet extends Bullet {
    public EnemyBullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel, direction);
    }

    public void hitPlayer() {
        List<Tank> bots = this.gamePanel.playerList;
        for (Tank bot : bots) {
            if (this.getRectangle().intersects(bot.getRectangle())) {
                this.gamePanel.blastList.add(new Blast("",bot.x-34,bot.y-14,this.gamePanel));
                this.gamePanel.playerList.remove(bot);
                this.gamePanel.removeList.add(this);
                bot.alive = false;
                break;
            }
        }

    }

    @Override
    public void paintSelft(Graphics graphics) {
        graphics.drawImage(img, x, y, null);
        this.go();
        this.hitPlayer();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
