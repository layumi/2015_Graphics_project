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
    
    class ThreadW implements Runnable{
        public void run(){
            //centerPanel.clear();
            centerPanel.CubeRotate('x',-1);
            //centerPanel.draw();
        }
    }
    
    class ThreadS implements Runnable{
        public void run(){
            //centerPanel.clear();
            centerPanel.CubeRotate('x',1);
            //centerPanel.draw();
        }
    }
    
    class ThreadA implements Runnable{
        public void run(){
            //centerPanel.clear();
            centerPanel.CubeRotate('y',1);
            //centerPanel.draw();
        }
    }
    
    class ThreadD implements Runnable{
        public void run(){
            //centerPanel.clear();
            centerPanel.CubeRotate('y',-1);
            //centerPanel.draw();
        }
    }
    
    class ThreadQ implements Runnable{
        public void run(){
            //centerPanel.clear();
            centerPanel.CubeRotate('z',-1);
            //centerPanel.draw();
        }
    }
    
    class ThreadE implements Runnable{
        public void run(){
            //centerPanel.clear();
            centerPanel.CubeRotate('z',1);
            //centerPanel.draw();
        }
    }
    
    public ScheduledExecutorService t1 = Executors.newScheduledThreadPool(1);
    public ScheduledExecutorService t2 = Executors.newScheduledThreadPool(2);
    public ScheduledExecutorService t3 = Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService t4 = Executors.newScheduledThreadPool(4);
    public ScheduledExecutorService t5 = Executors.newScheduledThreadPool(5);
    public ScheduledExecutorService t6 = Executors.newScheduledThreadPool(6);
    
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
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            t1 = Executors.newScheduledThreadPool(1);
            t1.scheduleAtFixedRate(new ThreadW(),0,30,TimeUnit.MILLISECONDS);
		}
        if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            t2 = Executors.newScheduledThreadPool(2);
            t2.scheduleAtFixedRate(new ThreadS(),0,30,TimeUnit.MILLISECONDS);
        }
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            t3 = Executors.newScheduledThreadPool(3);
            t3.scheduleAtFixedRate(new ThreadA(),0,30,TimeUnit.MILLISECONDS);
        }
        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            t4 = Executors.newScheduledThreadPool(4);
            t4.scheduleAtFixedRate(new ThreadD(),0,30,TimeUnit.MILLISECONDS);
        }
        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            t5 = Executors.newScheduledThreadPool(5);
            t5.scheduleAtFixedRate(new ThreadQ(),0,30,TimeUnit.MILLISECONDS);
        }
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
            t6 = Executors.newScheduledThreadPool(6);
            t6.scheduleAtFixedRate(new ThreadE(),0,30,TimeUnit.MILLISECONDS);
        }
    }

	public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            t1.shutdown();
        }
        if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            t2.shutdown();
        }
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            t3.shutdown();
        }
        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            t4.shutdown();
        }
        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            t5.shutdown();
        }
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
            t6.shutdown();
        }
    }

	public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
}