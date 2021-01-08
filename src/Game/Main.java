package Game;
import javax.swing.*;
import java.awt.*;


public class Main
{
    public static void main(String[] args)
    {
        LemmingsGame game = new LemmingsGame();
        LemmingsController controller = new LemmingsController(game);
        LemmingsGameView view = new LemmingsGameView(game, controller);

        controller.setLemmingsGameView(view);
        JFrame jframe = new JFrame("Lemmings");
        jframe.add(view);
        jframe.setBackground(Color.WHITE);
        jframe.setSize(view.getSize());        
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        game.gameLoop();
    }
}
