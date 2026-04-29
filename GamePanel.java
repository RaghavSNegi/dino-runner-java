import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    final int D_W = 1200;
    final int D_H = 550;

    Dino dino;
    Obstacle obstacle;

    Timer timerMove;
    Timer timerJump;

    int jumpY = 0;
    boolean jumping = false;
    boolean falling = false;

    boolean gameOver = false;
    int score = 0;
    // ☁️ clouds
    List<Integer> clouds = new ArrayList<>();

    public GamePanel() {

        setPreferredSize(new Dimension(D_W, D_H));
        setBackground(Color.WHITE);

        dino = new Dino(180, 400);
        obstacle = new Obstacle();

        // movement timer (like original jump++)
        timerMove = new Timer(40, this);
        timerMove.start();

        // jump physics timer (like original jumpY logic)
        timerJump = new Timer(80, e -> {
            int jumpBoost = score / 200; // increases slowly

        if (!gameOver && jumping) {

        if (falling) {
                jumpY -= (20 + jumpBoost);
        } else {
            jumpY += (20 + jumpBoost);
        }

        if (jumpY >= 280) {
            falling = true;
        }

        if(jumpY <= 0) {
                jumping = false;
                falling = false;
                jumpY = 0;
            }
    }
        });
        timerJump.start();

        // ☁️ clouds init
        for (int i = 0; i < 5; i++) {
            clouds.add((int)(Math.random() * D_W));
        }

        // ✅ SPACE key binding (reliable)
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "jump");

        am.put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver) {
                    restartGame();
                } else if (!jumping) {
                    jumping = true;
                    Sound.play("jump.wav");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            score++;
            obstacle.update(score);

            // collision check
            if (obstacle.collidesWith(dino.getBounds(jumpY))) {
                gameOver = true;
                Sound.play("gameover.wav");
            }

            updateClouds();
        }

        repaint();
    }

    private void updateClouds() {
        for (int i = 0; i < clouds.size(); i++) {
            int x = clouds.get(i) - 2;

            if (x < -50) x = D_W;

            clouds.set(i, x);
        }
    }

    private void drawClouds(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);

        for (int x : clouds) {
            g.fillOval(x, 50, 40, 20);
        }
    }

    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(900, 70, 80, 80);
    }

    private void drawGround(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(0, 400, 1700, 100);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("GAME OVER", 450, 150);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press SPACE to Restart", 430, 220);
    }

    private void restartGame() {
        gameOver = false;
        score = 0;
        jumpY = 0;
        jumping = false;
        falling = false;
        Obstacle.jump = 0;
        obstacle = new Obstacle();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawClouds(g);
        drawSun(g);
        drawGround(g);

        obstacle.draw(g);
        dino.draw(g, jumpY);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 1000, 50);
        if (gameOver) {
            drawGameOver(g);
        }
    }
}