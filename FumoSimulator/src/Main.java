import javax.swing.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting");

        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure that all processes close
        window.setResizable(false);
        
        GamePanel gp = new GamePanel();
        window.add(gp);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.startGameThread();
    }
}


