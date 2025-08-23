/*TODO
add character sprite and animation
add custom resolution options
fix issues with player speed when changing scale
*/

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize = 1; //tile
    final int scale = 10;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 120;
    final int maxScreenRow = 100;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //SET FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //SET PLAYER, SHOULD BE CENTER, CENTERED THE PLAYER 8/22
    int playerX = screenWidth/2 - tileSize/2;  
    int playerY = screenHeight/2 - tileSize/2;
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
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}