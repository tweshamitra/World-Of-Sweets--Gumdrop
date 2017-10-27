import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.imageio.*;
import java.util.ArrayList;

public class StartGUI {
    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private int numPlayers; 
    private ArrayList playerNames = new ArrayList();
    private int[] nums = {2,3,4};
    private JTextField text = new JTextField();
    private JTextField text_1 = new JTextField();
    private JTextField text_2 = new JTextField();
    private JTextField text_3 = new JTextField();
    private JTextField text_4 = new JTextField();
    private JPanel startPanel;
    private JComboBox num_players;
    private JFrame startFrame;
    public StartGUI(){
       
    }

    public int getNumPlayers(){
        return this.numPlayers;
    }

    public ArrayList getPlayerNames(){ 
        return this.playerNames;
    }

    public void drawStartGui(){
        startFrame = new JFrame("World of Sweets");
        startFrame.setSize(WIDTH, HEIGHT);
        startFrame.setResizable(false);
        startFrame.setLayout(new FlowLayout());
        startFrame.setBackground(Color.BLUE);
        startFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JLabel label = new JLabel("Choose number of players");
        num_players = new JComboBox();
        for(int i =0; i < nums.length; i++){
            num_players.addItem(nums[i]);
        }

        startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(0,2));
        startPanel.setSize(WIDTH,HEIGHT);
        startPanel.add(new JLabel("Welcome to World of Sweets!"));
        startPanel.add(new JLabel());

        startPanel.add(label);
        startPanel.add(num_players);
        startFrame.add(startPanel);
        startPanel.setBackground(Color.BLUE);

        startFrame.setVisible(true);

        JLabel label1 = new JLabel("Enter player names below");
        startPanel.add(label1);
        startFrame.setVisible(true);
      
        startPanel.add(text_1);
        startPanel.add(new JLabel());

        startPanel.add(text_2);
        startPanel.add(new JLabel());

        startPanel.add(text_3);
        startPanel.add(new JLabel());

        startPanel.add(text_4);
        startFrame.setVisible(true);

        JPanel submitPanel = new JPanel(new GridLayout(1,1));
        
       
        JButton submit = new JButton("Start game");
        ActionListener submitListener = new SubmitListener();
        submit.addActionListener(submitListener);
        submitPanel.add(submit);
        startFrame.add(submitPanel);
      

    }

    private class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
                numPlayers = (int)num_players.getSelectedItem();
                System.out.println(numPlayers);
                playerNames.add(text_1.getText());
                playerNames.add(text_2.getText());
                playerNames.add(text_3.getText());
                playerNames.add(text_4.getText());
                startFrame.getContentPane().removeAll();
            }
    }
}