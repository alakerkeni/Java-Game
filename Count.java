import java.awt.*;
public class Count {
   
     int secondsLeft;
    
    public Count(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    public void start(Graphics g, int x, int y) throws InterruptedException {
        Font font = new Font("Arial", Font.PLAIN, 100);
        g.setFont(font);
    
        for (int i = secondsLeft; i >= 1; i--) {
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(i), x, y);
            Thread.sleep(1000);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(i), x, y);
        }
        g.setColor(Color.BLACK);
        g.fillRect(x, y - font.getSize(), font.getSize(), font.getSize());
    }
    
}
