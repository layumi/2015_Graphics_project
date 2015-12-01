import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.*;

public class FillCube implements KeyListener {
	
	private JFrame mainFrame;
	private Graphics g;
	private Color background = new Color(255, 255, 190);
	public FillCube() {
		start();
	}
    
    class ThreadX implements Runnable{
        public void run(){
            centerPanel.clear();
            centerPanel.CubeRotateX();
            centerPanel.draw();
        }
    }
    
    class ThreadY implements Runnable{
        public void run(){
            centerPanel.clear();
            centerPanel.CubeRotateY();
            centerPanel.draw();
        }
    }
    
    class ThreadZ implements Runnable{
        public void run(){
            centerPanel.clear();
            centerPanel.CubeRotateZ();
            centerPanel.draw();
        }
    }
    
    public ScheduledExecutorService t1 = Executors.newScheduledThreadPool(1);
    public ScheduledExecutorService t2 = Executors.newScheduledThreadPool(2);
    public ScheduledExecutorService t3 = Executors.newScheduledThreadPool(3);
    
	public void start() {
		mainFrame = new JFrame("Reconstruct Cube");
		int width = Cons.mainWidth;
		int height = Cons.mainHeight;
		mainFrame.setSize(width, height);
		Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
		int mainx = (display.width - width) / 2;
		int mainy = (display.height - height) / 2;
		mainFrame.setLocation(mainx, mainy);
		Container cp = mainFrame.getContentPane(); 
		cp.add(createCenterPanel());
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.addKeyListener(this);
        //JButton X = new JButton("Hello~");
        //X.setBounds(100,100,100,100);
        //mainFrame.add(X);
	}
	
	private PaintPanel centerPanel;
	
	JPanel createCenterPanel() {
		centerPanel = new PaintPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		centerPanel.setBorder(BorderFactory.createLineBorder(background));
		return centerPanel;
	}
	
	public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            System.exit(0);
        }
        if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
            t1 = Executors.newScheduledThreadPool(1);
            t1.scheduleAtFixedRate(new ThreadX(),0,300,TimeUnit.MILLISECONDS);
		}
        if (e.getKeyChar() == 'y' || e.getKeyChar() == 'Y') {
            t2 = Executors.newScheduledThreadPool(2);
            t2.scheduleAtFixedRate(new ThreadY(),0,300,TimeUnit.MILLISECONDS);
        }
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
            t3 = Executors.newScheduledThreadPool(3);
            t3.scheduleAtFixedRate(new ThreadZ(),0,300,TimeUnit.MILLISECONDS);
        }
    }

	public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
            t1.shutdown();
        }
        if (e.getKeyChar() == 'y' || e.getKeyChar() == 'Y') {
            t2.shutdown();
        }
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
            t3.shutdown();
        }
    }

	public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
}