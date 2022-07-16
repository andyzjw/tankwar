package com.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JFrame {
    Image offScreemImage = null;


    int width = 800;
    int height = 610;

    Image select = Toolkit.getDefaultToolkit().getImage("image/2.png");
    int y = 150;
    // 0 游戏未开始  1 单人模式  2 双人模式 5 胜利
    int state = 0;
    int a = 1;
    // 重绘次数
    int count;

    int emenyCount;
    List<Bullet> bulletList = new ArrayList<Bullet>();
    List<Bot> botList = new ArrayList<>();
    List<Bullet> removeList = new ArrayList<>();
    List<Tank> playerList = new ArrayList<>();
    List<Wall> wallList = new ArrayList<>();
    List<Base> baseList = new ArrayList<>();
    List<Blast> blastList = new ArrayList<>();
    // 玩家1
    PlayerOne playerOne = new PlayerOne("image/1.png", 125, 510, this,
            "image/1.png", "image/4.png", "image/2.png", "image/3.png");

    Base base = new Base("image/8.png", 365, 560, this);

    public void launch() {
        setTitle("坦克大战啊");
        setSize(width, height);
        // 设置屏幕居中
        setLocationRelativeTo(null);
        // 添加关闭
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //添加键盘监听器
        this.addKeyListener(new GamePanel.KeyMonitor());

        for (int i = 0; i < 14; i++) {
            wallList.add(new Wall("image/7.png", 50 + i * 50, 170, this));
        }
        wallList.add(new Wall("image/7.png", 305, 560, this));
        wallList.add(new Wall("image/7.png", 305, 500, this));
        wallList.add(new Wall("image/7.png", 365, 500, this));
        wallList.add(new Wall("image/7.png", 425, 500, this));
        wallList.add(new Wall("image/7.png", 425, 560, this));
        int emenyGoal = 10;
        baseList.add(base);
        while (true) {
            if (botList.size() == 0 && emenyCount == emenyGoal) {
                state = 5;
            }

            if (playerList.size() == 0 && (state == 1 || state == 2) || baseList.size() == 0) {
                state = 4;
            }

            if (count % 100 == 1 && emenyCount < emenyGoal && (state == 1 || state == 2)) {
                Random random = new Random();
                int location = random.nextInt(800);
                botList.add(new Bot("image/enemy/1.png", location, 110, this, "image/enemy/1.png", "image/enemy/4.png",
                        "image/enemy/2.png", "image/enemy/3.png"));
                emenyCount++;
            }

            repaint();
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void paint(Graphics g) {
//        System.out.println(bulletList.size());
        if (offScreemImage == null) {
            offScreemImage = this.createImage(width, height);
        }
        Graphics graphics = offScreemImage.getGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("仿宋", Font.BOLD, 50));

        if (state == 0) {
            graphics.drawString("选择游戏模式", 220, 100);
            graphics.drawString("单人模式", 220, 200);
            graphics.drawString("双人模式", 220, 300);

            graphics.drawImage(select, 160, y, null);
        } else if (state == 1 || state == 2) {
            graphics.drawString("游戏开始", 220, 100);
            if (state == 1) {
                graphics.drawString("单人模式", 220, 200);

            } else {
                graphics.drawString("双人模式", 220, 200);
            }
            // 添加游戏元素
            playerList.forEach(playerOne -> {
                playerOne.paintSelft(graphics);
            });

            for (Bullet b : bulletList) {
                b.paintSelft(graphics);
            }
            bulletList.removeAll(removeList);

            botList.forEach(bot1 -> {
                bot1.paintSelft(graphics);
            });

            wallList.forEach(wall -> {
                wall.paintSelft(graphics);
            });

            baseList.forEach(base -> {
                base.paintSelft(graphics);
            });

            blastList.forEach(blast -> {
                blast.paintSelft(graphics);
            });
            count++;

        } else if (state == 3) {
            graphics.drawString("游戏暂停", 220, 200);
        } else if (state == 4) {
            graphics.drawString("游戏失败", 220, 200);
        } else if (state == 5) {
            graphics.drawString("游戏胜利", 220, 200);
        }

        g.drawImage(offScreemImage, 0, 0, null);

    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_1:
                    a = 1;
                    y = 150;
                    break;
                case KeyEvent.VK_2:
                    a = 2;
                    y = 250;
                    break;
                case KeyEvent.VK_ENTER:
                    state = a;
                    playerList.add(playerOne);
                    break;
                case KeyEvent.VK_P:
                    if (state != 3) {
                        a = state;
                        state = 3;
                    } else {
                        state = a;
                        if (a == 0) {
                            a = 1;
                        }
                    }
                default:
                    playerOne.keyPressed(e);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            playerOne.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}
