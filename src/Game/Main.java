package Game;
import javax.swing.*;
import java.awt.*;


public class Main
{
    public static void main(String[] args)
    {
        LemmingsGameObservable game = new LemmingsGameObservable();
        LemmingsController controller = new LemmingsController(game);
        LemmingsGameView view = new LemmingsGameView(game, controller);
        game.registerObserver(view);
        controller.setLemmingsGameView(view);

        JFrame jframe = new JFrame("Lemmings");
        jframe.add(view);
        jframe.setBackground(Color.WHITE);
        jframe.setSize(view.getSize());        
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.pack();

        while (true)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            game.step();
            game.notifyObservers();
        }
    }
}
