import java.awt.*;
import java.awt.event.*;
import java.util.*;


import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    Count countdown;
    boolean gameStarted;
    int gameMode;
    int count;
    boolean gamePaused=false;
    private long lastTime;
    
    

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newCount(){
        countdown = new Count(3);
    }
    
    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),BALL_DIAMETER, BALL_DIAMETER);

    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,PADDLE_HEIGHT, 2);

    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        draw(g);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); 
    }

    public void move() {
        if (!gamePaused) {
            paddle1.move();
            paddle2.move();
            ball.move();
        }
            
        }

    public void checkCollision() {

        //contrôle de bordures sur l'axe des y

        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
            /*add sound effect */
            
        }

        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

         //contrôle de bordures sur l'axe des x

        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; 
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //rebond sur les raquettes
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; 
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //raquette toujours à l'intérieur du cadre
        if (paddle1.y <= 0)
            paddle1.y = 0;

        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;

        if (paddle2.y <= 0)
            paddle2.y = 0;

        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

    

        if (ball.x <= 0) {
            score.player2++;
            
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
            
        }

        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;

            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
            
        }

    }

    // boucle
    public void run() {
        lastTime = System.nanoTime();
        double amountOfTicks = 60.0; //fps
        double ns = 1000000000 / amountOfTicks; //durée de chaque image
        double delta = 0;
        
        while (true) {
            while (gamePaused) {
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }

        }

    }

    public void togglePause() {
        gamePaused = !gamePaused;
    }
    
    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            // press key P to pause
            if (e.getKeyCode() == KeyEvent.VK_P) {
                gamePaused = !gamePaused;
                if (!gamePaused) {
                    lastTime = System.nanoTime();
                }
            }
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

    }

    

    

}