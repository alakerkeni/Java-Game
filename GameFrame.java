import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class GameFrame extends JFrame{

	static GamePanel panel;
	Accueil accueil;
	PlayerSetup playersetup;

	GameFrame(){
		
		accueil= new Accueil();
		this.add(accueil);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		accueil.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accueil.play = true;
                remove(accueil);
                if(!Accueil.lightmode){
					setBackground(Color.black);
				}
				
                //playersetup = new PlayerSetup();
				panel= new GamePanel();
                add(panel);
				panel.requestFocus();
                revalidate();
                repaint();
            }
        });
		
	}
}