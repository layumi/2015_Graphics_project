import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel {

	private Color c0 = new Color(0, 0, 0);
	private Color c1 = new Color(0, 0, 255);
	private Color c2 = new Color(0, 255, 0);
    private Color c3 = new Color(0, 255, 255);
    private Color c4 = new Color(255, 0, 0);
    private Color c5 = new Color(255, 0, 255);
    private Color c6 = new Color(255, 255,0);
    //------   1-2   5-6
    //------   | |   | |
    //------   3-4   7-8
    int half=50;
    private Point p1= new Point(270,270,-50);
    private Point p2= new Point(370,270,-50);
    private Point p3= new Point(270,370,-50);
    private Point p4= new Point(370,370,-50);
    private Point p5= new Point(270,270,50);
    private Point p6= new Point(370,270,50);
    private Point p7= new Point(270,370,50);
    private Point p8= new Point(370,370,50);
    
	private int N,max_z=-9999;
	private int x0 = -1, y0 = -1, x1 = -1, y1 = -1;
	
	private boolean drawing = false;
    private int finish =0;
    
	private BufferedImage image;
    private Image image_buffer;
	private Graphics ig;
    private Graphics ig_buffer;
	private Graphics2D ig2d;
    private Graphics2D ig2d_buffer;
	
	public PaintPanel() {  //initial a cube
        image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_RGB);
        ig = image.getGraphics();
        ig2d = (Graphics2D) ig;
        clear();
        draw();
	}
    
    private void find_max_z(){   //found the max_z. That point should not be showed.
        max_z = p1.z;
        if(max_z<p2.z)  max_z = p2.z;
        if(max_z<p3.z)  max_z = p3.z;
        if(max_z<p4.z)  max_z = p4.z;
        if(max_z<p5.z)  max_z = p5.z;
        if(max_z<p6.z)  max_z = p6.z;
        if(max_z<p7.z)  max_z = p7.z;
        if(max_z<p8.z)  max_z = p8.z;
    }
    
    public void draw(){  //draw edges
        find_max_z();
        finish=0;
        clear();
        if(p1.z<max_z && p2.z<max_z)   drawLine(p1,p2,c0);
        if(p2.z<max_z && p4.z<max_z)   drawLine(p2,p4,c0);
        if(p4.z<max_z && p3.z<max_z)   drawLine(p4,p3,c0);
        if(p3.z<max_z && p1.z<max_z)   drawLine(p3,p1,c0);
        if(p5.z<max_z && p6.z<max_z)   drawLine(p5,p6,c0);
        if(p6.z<max_z && p8.z<max_z)   drawLine(p6,p8,c0);
        if(p8.z<max_z && p7.z<max_z)   drawLine(p8,p7,c0);
        if(p7.z<max_z && p5.z<max_z)   drawLine(p7,p5,c0);
        if(p1.z<max_z && p5.z<max_z)   drawLine(p1,p5,c0);
        if(p2.z<max_z && p6.z<max_z)   drawLine(p2,p6,c0);
        if(p3.z<max_z && p7.z<max_z)   drawLine(p3,p7,c0);
        if(p4.z<max_z && p8.z<max_z)   drawLine(p4,p8,c0);
        //draw surface
        if(p1.z<max_z && p2.z<max_z && p4.z<max_z && p3.z<max_z)  {
            fill_square(p1,p2,p4,p3,c1);
        }
        if(p1.z<max_z && p5.z<max_z && p7.z<max_z && p3.z<max_z)  {
            fill_square(p1,p5,p7,p3,c2);
        }
        if(p1.z<max_z && p5.z<max_z && p6.z<max_z && p2.z<max_z)  {
            fill_square(p1,p5,p6,p2,c3);
        }
        if(p2.z<max_z && p6.z<max_z && p8.z<max_z && p4.z<max_z)  {
            fill_square(p2,p6,p8,p4,c4);
        }
        if(p3.z<max_z && p7.z<max_z && p8.z<max_z && p4.z<max_z)  {
            fill_square(p3,p7,p8,p4,c5);
        }
        if(p5.z<max_z && p6.z<max_z && p8.z<max_z && p7.z<max_z)  {
            fill_square(p5,p6,p8,p7,c6);
        }
        finish=1;
        //ig = ig_buffer;
        //paint(ig_buffer);
    }
    
    public void fill_square(Point pp1,Point pp2, Point pp3, Point pp4, Color c){  //fill color for one surface
        int[] x, y;
        x = new int[Cons.polySize];
        y = new int[Cons.polySize];
        x[0] = pp1.x; y[0] = pp1.y;
        x[1] = pp2.x; y[1] = pp2.y;
        x[2] = pp3.x; y[2] = pp3.y;
        x[3] = pp4.x; y[3] = pp4.y;
        fillPolygon(x,y,c);
    }
    
    public void CubeRotateX(int angle){
        p1.RotateX(angle);
        p2.RotateX(angle);
        p3.RotateX(angle);
        p4.RotateX(angle);
        p5.RotateX(angle);
        p6.RotateX(angle);
        p7.RotateX(angle);
        p8.RotateX(angle);
    }
    
    public void CubeRotateY(int angle){
        p1.RotateY(angle);
        p2.RotateY(angle);
        p3.RotateY(angle);
        p4.RotateY(angle);
        p5.RotateY(angle);
        p6.RotateY(angle);
        p7.RotateY(angle);
        p8.RotateY(angle);
    }
    
    public void CubeRotateZ(int angle){
        p1.RotateZ(angle);
        p2.RotateZ(angle);
        p3.RotateZ(angle);
        p4.RotateZ(angle);
        p5.RotateZ(angle);
        p6.RotateZ(angle);
        p7.RotateZ(angle);
        p8.RotateZ(angle);
    }

    private void drawPixel(Point p,Color c){
        drawPixel(p.x, p.y, c);
    }
    
	private void drawPixel(int x, int y, Color c) {
		ig.setColor(c);
		ig.fillRect(x, y, 1, 1);
	}
	
    private void drawLine(Point p1,Point p2, Color c){
        drawLine(p1.x, p1.y,p2.x,p2.y, c);
    }
    
	private void drawLine(int x0, int y0, int x1, int y1, Color c) {
		if (x0 > x1) {
			int temp;
			temp = x0; x0 = x1; x1 = temp;
			temp = y0; y0 = y1; y1 = temp;
		}
        double k = Math.abs(y1 - y0)*1.0 / Math.abs(x1 - x0);
		if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
			//0 < k < 1
			if (y0 < y1) {
				int a = y0 - y1, b = x1 - x0;
				int d = 2 * a + b, d1 = 2 * a, d2 = 2 * (a + b);
                int x = x0, y = y0;
				drawPixel(x, y, c);
				while (x < x1) {
					if (d < 0) {
                        x ++;
                        y ++;
                        d += d2;
					}
					else {
                        x ++;
                        d += d1;
					}
                    drawPixel(x, y, c);
				}
			}
			else {
				int a = y1 - y0, b = x1 - x0;
				int d = 2 * a + b, d1 = 2 * a, d2 = 2 * (a + b);
				int x = x0, y = y0;
				drawPixel(x, y, c);
				while (x < x1) {
					if (d < 0) {
						x ++;
						y --;
						d += d2;
					}
					else {
						x ++;
						d += d1;
					}
					drawPixel(x, y, c);
				}
			}
		}
		else {
			//k>=1
			if (y0 < y1) {
				int a = y0 - y1, b = x1 - x0;
				int d = a + 2 * b, d1 = 2 * b, d2 = 2 * (a + b);
				int x = x0, y = y0;
				drawPixel(x, y, c);
				while (y < y1) {
					if (d < 0) {
						y ++;
						d += d1;
					}
					else {
						x ++;
						y ++;
						d += d2;
					}
					drawPixel(x, y, c);
				}
			}
			else {
				int a = y1 - y0, b = x1 - x0;
				int d = a + 2 * b, d1 = 2 * b, d2 = 2 * (a + b);
				int x = x0, y = y0;
				drawPixel(x, y, c);
				while (y > y1) {
					if (d < 0) {
						y --;
						d += d1;
					}
					else {
						x ++;
						y --;
						d += d2;
					}
					drawPixel(x, y, c);
				}
			}
		}
	}
	public void paint(Graphics g) {
		//super.paint(g);
        //ig2d = (Graphics2D) ig_buffer;
		Graphics2D g2d = (Graphics2D) g;
		ig2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(finish ==1 ) g.drawImage(image, 0, 0, this);
        //super.paint(g);
	}
	
    public void fillPolygon(int[] x, int[] y,Color c) {
        N=4;// 4 points
        Edge[] newEdge = new Edge[Cons.polySize];
        int ymin = -1, ymax = -1;
        for (int i = 0; i < N; i ++) {
            if (ymin == -1 || y[i] < ymin)
                ymin = y[i];
            if (ymax == -1 || y[i] > ymax)
                ymax = y[i];
            if (i == 0)
                newEdge[i] = new Edge(x[N - 1], y[N - 1], x[0], y[0]);
            else
                newEdge[i] = new Edge(x[i - 1], y[i - 1], x[i], y[i]);
        }
        
        for (int i = 0; i < N; i ++) {
            for (int j = i + 1; j < N; j ++) {
                if (newEdge[i].y0 > newEdge[j].y0) {
                    newEdge[i].swap(newEdge[j]);
                }
            }
        }
        
        ActiveEdge[] activeEdge = new ActiveEdge[Cons.polySize];
        int M = 0;
        for (int y_tmp= ymin, i = 0; y_tmp < ymax; y_tmp ++) {
            int j, k;
            for ( ; i < N && newEdge[i].y0 == y_tmp; i ++) {
                if (activeEdge[M] == null)
                    activeEdge[M] = new ActiveEdge(newEdge[i]);
                else
                    activeEdge[M].copy(newEdge[i]);
                M ++;
            }
            
            for (int l = 0; l < M; l ++) {
                for (int r = l + 1; r < M; r ++) {
                    if (activeEdge[l].x > activeEdge[r].x) {
                        activeEdge[l].swap(activeEdge[r]);
                    }
                }
            }
            
            boolean flag = false;
            for (j = 0; j + 1 < M; j ++) {
                flag = ! flag;
                if (Math.abs(activeEdge[j].x - activeEdge[j + 1].x) < Cons.eps) {
                    if (activeEdge[j].y1 == y_tmp && activeEdge[j + 1].y1 == y_tmp) {
                        //0
                    }
                    else if (activeEdge[j].y1 > y_tmp && activeEdge[j + 1].y1 > y_tmp) {
                        //2
                        int lx = (int)activeEdge[j].x;
                        drawLine(lx, y_tmp, lx, y_tmp, c);
                        repaint();
                    }
                    else {
                        //1
                        int lx = (int)activeEdge[j].x;
                        drawLine(lx, y_tmp, lx, y_tmp, c);
                        repaint();
                        flag = ! flag;
                    }
                }
                else {
                    if (flag) {
                        int lx = (int)(activeEdge[j].x + 0.5);
                        int rx = (int)(activeEdge[j + 1].x - 0.5);
                        if (lx <= rx) drawLine(lx, y_tmp, rx, y_tmp, c);
                        repaint();
                    }
                }
            }
            
            for (j = 0; j < M; ) {
                if (activeEdge[j].y1 > y_tmp) {
                    activeEdge[j].x += activeEdge[j].delta;
                    j ++;
                }
                else if (activeEdge[j].y1 == y_tmp) {
                    //delete
                    activeEdge[j].swap(activeEdge[M - 1]);
                    M --;
                }
            }
        }
        
        for (int i = 0; i < N; i ++) {
            if (i == 0) {
                drawLine(x[N - 1], y[N - 1], x[0], y[0], c0);
                repaint();
            }
            else {
                drawLine(x[i - 1], y[i - 1], x[i], y[i], c0);
                repaint();
            }
        }
    }
    
	public void clear() {
		ig.setColor(Color.white);
		ig.fillRect(0, 0, 640, 640);
		//drawing = false;
		repaint();
	}
}
