package Game;
import javax.swing.*;
import java.awt.*;


public class Main
{
    public static void main(String[] args)
    {
        LemmingsGameView view = new LemmingsGameView(new LemmingsGame());
        JFrame jframe = new JFrame("Lemmings");
        jframe.add(view);
        jframe.setBackground(Color.WHITE);
        jframe.setSize(view.getSize());        
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        view.play();
        
        
    }
    
    
    
}
