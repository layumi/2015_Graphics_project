
public class Point {
	int x, y, z;
    double x0,y0,z0;
    int centerx = 320;
    int centery = 320;
	public Point(int x, int y, int z) {
        this.x = x; this.y = y;this.z = z;
        x0 = x - centerx;
        y0 = y - centery;
        z0 = z;
	}
    public void update(){
        x = (int)(x0 + centerx);
        y = (int)(y0 + centery);
        z = (int)z0;
    }
    public void RotateX(double theta){
        double t = theta / 180 *Math.PI;
        x0 = x0;
        y0 = y0 * Math.cos(t) - z0 * Math.sin(t);
        z0 = y0 * Math.sin(t) + z0 * Math.cos(t);
        update();
    }
    public void RotateY(double theta){
        double t = theta / 180 *Math.PI;
        y0 = y0;
        x0 = x0 * Math.cos(t) + z0 * Math.sin(t);
        z0 = -x0 * Math.sin(t) + z0 * Math.cos(t);
        update();
    }
    public void RotateZ(double theta){
        double t = theta / 180 *Math.PI;
        z0 = z0;
        y0 = y0 * Math.cos(t) + x0 * Math.sin(t);
        x0 = -y0 * Math.sin(t) + x0 * Math.cos(t);
        update();
    }
}
