import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class PlayerSetup extends JPanel{
    private String name;
    private Color paddleColor;
    private int i =0;
    private JButton [] colorButton1;
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private JPanel player1, player2;
    private JLabel nameLabel1,nameLabel2;
    private JTextField name1, name2;
    boolean clicked= false;
    
    boolean ready1=false;
    boolean ready2=false;
    boolean lightmode=true;
    
    public PlayerSetup(){
        super();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

        player1 = new JPanel(new BorderLayout());
        this.add(player1,BorderLayout.NORTH);
        add(Box.createVerticalGlue());

        player2 = new JPanel(new BorderLayout());
        this.add(player2,BorderLayout.SOUTH);
         add(Box.createVerticalGlue());
        
        colorButton1 = new JButton[4];
        
        name1 = new JTextField(20);
        name1.setPreferredSize(new Dimension(20, 30));
        name1.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
        
        nameLabel1 = new JLabel("Player 1 Name:");
        nameLabel1.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));

        JPanel panel1= new JPanel();
        panel1.setLayout(new FlowLayout());
        
        panel1.add(nameLabel1,BorderLayout.CENTER);
        panel1.add(name1,BorderLayout.CENTER);
        panel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JPanel panel2= new JPanel();
        panel2.setLayout(new FlowLayout());
        
        for (Color color : colors) {
            colorButton1[i] = new JButton();
            colorButton1[i].setBackground(color);
            colorButton1[i].setUI(new StyledButtonUI());
            colorButton1[i].setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
            colorButton1[i].setForeground(Color.white);
            colorButton1[i].setPreferredSize(new Dimension(50, 50));
            colorButton1[i].addActionListener((e) -> {
                paddleColor = color;
                System.out.println(paddleColor);
            });
            panel2.add(colorButton1[i++]);
        }
        
        JPanel panel3= new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JButton savebutton1 = new JButton("SAVE");
        savebutton1.setBackground(Color.BLACK);
        savebutton1.setUI(new StyledButtonUI());
        savebutton1.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
        savebutton1.setForeground(Color.WHITE);
        savebutton1.setPreferredSize(new Dimension(150, 50));
        savebutton1.addActionListener((e) -> {
            if (!ready1)
            {
                String text = "READY \u2713";
                savebutton1.setText(text);
                savebutton1.setBackground(Color.GREEN);
                name1.setEditable(false);
                ready1=true;
                System.out.println(ready1);
            }
            else
            {
                savebutton1.setText("SAVE");
                savebutton1.setBackground(Color.BLACK);
                savebutton1.setForeground(Color.WHITE);
                name1.setEditable(true);
                if (!Accueil.lightmode)
                {
                    savebutton1.setBackground(Color.white);
                    savebutton1.setForeground(Color.BLACK);
                }
                ready1=false;

            }

            });
            panel3.add(savebutton1, BorderLayout.EAST);


        player1.add(panel2,  BorderLayout.CENTER);
        player1.add(panel1,  BorderLayout.NORTH);
        player1.add(panel3,  BorderLayout.SOUTH);

            // palayer 2

            name2 = new JTextField(20);
            name2.setPreferredSize(new Dimension(20, 30));
            name2.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
            
            nameLabel2 = new JLabel("Player 2 Name:");
            nameLabel2.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
    
            JPanel panel4= new JPanel();
            panel4.setLayout(new FlowLayout());
            
            panel4.add(nameLabel2,BorderLayout.CENTER);
            panel4.add(name2,BorderLayout.CENTER);
            panel4.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

            JPanel panel5= new JPanel();
            panel5.setLayout(new FlowLayout());
            for (Color color : colors) {
                JButton colorButton2 = new JButton();
                colorButton2.setBackground(color);
                colorButton2.setUI(new StyledButtonUI());
                colorButton2.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
                colorButton2.setForeground(Color.white);
                colorButton2.setPreferredSize(new Dimension(50, 50));
                colorButton2.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
                colorButton2.addActionListener((e) -> {

                });
                panel5.add(colorButton2);
            }
            
            JPanel panel6= new JPanel();
            panel6.setLayout(new BorderLayout());
            panel6.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
    
            JButton savebutton2 = new JButton("SAVE");
            savebutton2.setBackground(Color.BLACK);
            savebutton2.setUI(new StyledButtonUI());
            savebutton2.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
            savebutton2.setForeground(Color.WHITE);
            savebutton2.setPreferredSize(new Dimension(150, 50));
            savebutton2.addActionListener((e) -> {
               if (!ready2)
               {
                   String text = "READY \u2713";
                   savebutton2.setText(text);
                   savebutton2.setBackground(Color.GREEN);
                   name2.setEditable(false);
                   ready2=true;
               }
                
            else
            {
                savebutton2.setText("SAVE");
                savebutton2.setBackground(Color.BLACK);
                savebutton2.setForeground(Color.WHITE);
                name2.setEditable(true);
                ready2=false;
                if (!Accueil.lightmode)
                {
                    savebutton2.setBackground(Color.white);
                    savebutton2.setForeground(Color.BLACK);
                }

            }
                });
                panel6.add(savebutton2, BorderLayout.EAST);
    
    
            player2.add(panel5,  BorderLayout.CENTER);
            player2.add(panel4,  BorderLayout.NORTH);
            player2.add(panel6,  BorderLayout.SOUTH);

            //appliquer le dark mode
            if (! Accueil.lightmode)
            {
            savebutton1.setBackground(Color.white);
            savebutton1.setForeground(Color.black);

            savebutton2.setBackground(Color.white);
            savebutton2.setForeground(Color.black);

            panel1.setBackground(Color.BLACK);
            panel2.setBackground(Color.BLACK);
            panel3.setBackground(Color.BLACK);
            panel4.setBackground(Color.BLACK);
            panel5.setBackground(Color.BLACK);
            panel6.setBackground(Color.BLACK);

            nameLabel1.setForeground(Color.RED);
            nameLabel2.setForeground(Color.GREEN);

            this.setBackground(Color.black);
            }
        
    }

    public static class StyledButtonUI extends BasicButtonUI {

        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorder(new EmptyBorder(5, 15, 5, 15));
        }

        @Override
        public void paint (Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            super.paint(g, c);
        }

        private void paintBackground (Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
        }
    }
}
