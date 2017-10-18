import java.awt.*;
import javax.swing.*;

public class GameBoard{
    private final int height = 800;
    private final int width = 800;
    
    private JFrame frame = new JFrame("World of Sweets");
    public GameBoard(){
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.pink);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(0,20));
        frame.setVisible(true);
    }
}