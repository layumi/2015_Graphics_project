import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class FillCube implements KeyListener {
	
	private JFrame mainFrame;
	private Graphics g;
	private Color background = new Color(255, 255, 190);
	public FillCube() {
		start();
	}
	
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
            centerPanel.clear();
            centerPanel.CubeRotateX();
            centerPanel.draw();
		}
        if (e.getKeyChar() == 'y' || e.getKeyChar() == 'Y') {
            centerPanel.clear();
            centerPanel.CubeRotateY();
            centerPanel.draw();
        }
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
            centerPanel.clear();
            centerPanel.CubeRotateZ();
            centerPanel.draw();
        }
    }

	public void keyReleased(KeyEvent e) {
    }

	public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
}