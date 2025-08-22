import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    final int titleSize = originalTileSize * scale; //48x48 title
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol; //768
    final int screenHeight = titleSize * maxScreenRow; //576

    //SET FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //SET PLAYER
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //SET WINDOW
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //GAME RUN TIME
    @Override
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null)
        {
            //long currentTime = System.nanoTime();
            //System.out.println("Current Time: " + currentTime);
            //System.out.println("The game loop is running");

            //1 UPDATE
            update();

            //2 DRAW
            repaint();

            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void update()
    {
        if(keyH.upPressed == true)
            playerY -= playerSpeed;
        else if(keyH.downPressed == true)
            playerY += playerSpeed;
        else if(keyH.leftPressed == true)
            playerX -= playerSpeed;
        else if(keyH.rightPressed == true)
            playerX += playerSpeed;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, titleSize, titleSize);

        g2.dispose();
    }
}