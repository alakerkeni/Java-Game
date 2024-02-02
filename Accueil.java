import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Accueil extends JPanel {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    Thread gameThread;
    JButton button1,button2,button3;
    ActionListener buttonListener;
    static boolean lightmode= true;
    boolean play= false;
    Clip clip;

    Accueil() {
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.WHITE);
        gameThread = new Thread();
        gameThread.start();
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
      
        Dimension buttonSize = new Dimension(200, 50);
        
        buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle button click event
                System.out.println("Button clicked: " + e.getActionCommand());
            }
        };
        
        button1 = new JButton("PLAY");
        button1.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
        button1.setBackground(Color.black);
        button1.setForeground(Color.white);
        button1.setUI(new StyledButtonUI());
        button1.setPreferredSize(buttonSize);
        button1.addActionListener(buttonListener);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 0, 10, 0);
        this.add(button1, gbc);

        button2 = new JButton("DARK MODE");
        button2.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
        button2.setBackground(Color.black);
        button2.setForeground(Color.white);
        button2.setUI(new StyledButtonUI());
        button2.setPreferredSize(buttonSize);
        button2.addActionListener(buttonListener);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        this.add(button2, gbc);

        button3 = new JButton("LIGHT MODE");
        button3.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
        button3.setBackground(Color.black);
        button3.setForeground(Color.white);
        button3.setUI(new StyledButtonUI());
        button3.setPreferredSize(buttonSize);
        button3.addActionListener(buttonListener);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 0); 
        this.add(button3, gbc);
        //Actionliteners
        button2.addActionListener(e -> {
            button2.setBackground(Color.white);
            button2.setForeground(Color.black);

            button3.setBackground(Color.white);
            button3.setForeground(Color.black);

            button1.setBackground(Color.white);
            button1.setForeground(Color.black);

            this.setBackground(Color.black);
            
            lightmode=false;
        });

        button3.addActionListener(e -> {
           if(!lightmode)
            {
                button2.setBackground(Color.black);
                button2.setForeground(Color.white);
    
                button3.setBackground(Color.black);
                button3.setForeground(Color.white);
    
                button1.setBackground(Color.black);
                button1.setForeground(Color.white);
    
                this.setBackground(Color.white);
    
                lightmode=true;

            }
        });
        
        /*button1.addActionListener(e -> {
            play=true;
         });*/

    }
    public void PlaySound(){
        try {
            javax.sound.sampled.AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("pingpongbat.ogg"));
            if (audioInputStream != null) {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } else {
                System.err.println("Error: audioInputStream is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
