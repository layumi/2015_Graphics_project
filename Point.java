
public class Point {
	int x, y, z;
    private double x0,y0,z0;
    int centerx = 320;
    int centery = 320;
	public Point(int x, int y, int z) {
        this.x = x; this.y = y;this.z = z;
        x0 = x - centerx;
        y0 = y - centery;
        z0 = z;
	}
    public synchronized void Rotate(char v,int angle){
        if(v=='x') RotateX(angle);
        else if(v=='y') RotateY(angle);
        else if(v=='z') RotateZ(angle);
    }
    private synchronized void update(){
        x = (int)(x0 + centerx + 0.500);
        y = (int)(y0 + centery + 0.500);
        z = (int)z0;
    }
    private void RotateX(double theta){
        double t = Math.toRadians(theta);
        x0 = x0;
        y0 = y0 * Math.cos(t) - z0 * Math.sin(t);
        z0 = y0 * Math.sin(t) + z0 * Math.cos(t);
        update();
    }
    private void RotateY(double theta){
        double t = Math.toRadians(theta);
        y0 = y0;
        x0 = x0 * Math.cos(t) + z0 * Math.sin(t);
        z0 = -x0 * Math.sin(t) + z0 * Math.cos(t);
        update();
    }
    private void RotateZ(double theta){
        double t = Math.toRadians(theta);
        z0 = z0;
        y0 = y0 * Math.cos(t) + x0 * Math.sin(t);
        x0 = -y0 * Math.sin(t) + x0 * Math.cos(t);
        update();
    }
}
