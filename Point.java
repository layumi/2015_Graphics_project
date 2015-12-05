
public class Point {
	int x, y, z,node;
    double xt,yt,zt;//temp
    private int anglex=0,angley=0,anglez=0;
    private double x0,y0,z0;
    int centerx = 320;
    int centery = 320;
	public Point(int x, int y, int z , int node) {  //initial
        this.x = x; this.y = y;this.z = z; this.node = node;
        x0 = x - centerx;
        y0 = y - centery;
        z0 = z;
        xt = x0;
        yt = y0;
        zt = z0;
	}
    public synchronized void Rotate(char v,int theta){
        //initial state
        //if(v == 'x') anglex += theta;
        //else if(v == 'y') angley += theta;
        //else if(v == 'z') anglez += theta;
        xt=x0;yt=y0;zt=z0;
        Rotate(anglex,angley,anglez);
        
        if(v == 'x') {anglex += theta;RotateX(theta);}
        else if(v == 'y') {angley += theta;RotateY(theta);}
        else if(v == 'z') {anglez += theta;RotateZ(theta);}
        
        update();
    }
    public void Rotate(int ax,int ay, int az){
        double tmpx = ax * Math.PI / 180.0;
        double tmpy = ay * Math.PI / 180.0;
        double tmpz = az * Math.PI / 180.0;
        double xt0=xt,yt0=yt,zt0=zt;
        xt = xt0 * (Math.cos(tmpy) * Math.cos(tmpz) - Math.sin(tmpx) * Math.sin(tmpy) * Math.sin(tmpz)) - yt0 * Math.cos(tmpx) * Math.sin(tmpz) + zt0 * (Math.sin(tmpy) * Math.cos(tmpz) + Math.sin(tmpx) * Math.cos(tmpy) * Math.sin(tmpz));
        yt = xt0 *(Math.cos(tmpy) * Math.sin(tmpz) + Math.sin(tmpx) * Math.sin(tmpy)*Math.cos(tmpz)) + yt0 * Math.cos(tmpx)*Math.cos(tmpz) + zt0 * ( Math.sin(tmpy) * Math.sin(tmpz) - Math.sin(tmpx) * Math.cos(tmpy) * Math.cos(tmpz));
        zt = -xt0 * Math.cos(tmpx) * Math.sin(tmpy) + yt0 * Math.sin(tmpx) + zt0 * Math.cos(tmpx) * Math.cos(tmpy);
    }
    
    private synchronized void update(){  //show
        x = (int)(xt + centerx + 0.500);  //
        y = (int)(yt + centery + 0.500);  //
        z = (int)zt;
    }
    //ro==z theta==y fa==x
    private void RotateX(int angle){
        double tmp1 = angle * Math.PI / 180.0;
        double xt0=xt,yt0=yt,zt0=zt;
        xt = xt0;
        yt = yt0 * Math.cos(tmp1) - zt0 * Math.sin(tmp1);
        zt = yt0 * Math.sin(tmp1) + zt0 * Math.cos(tmp1);
    }
    private void RotateY(int angle){
        double tmp2 = angle * Math.PI / 180.0;
        double xt0=xt,yt0=yt,zt0=zt;
        yt = yt0;
        xt = xt0 * Math.cos(tmp2) + zt0 * Math.sin(tmp2);
        zt = -xt0 * Math.sin(tmp2) + zt0 * Math.cos(tmp2);
    }
    private void RotateZ(int angle){
        double tmp3 = angle * Math.PI / 180.0;
        double xt0=xt,yt0=yt,zt0=zt;
        zt = zt0;
        yt = yt0 * Math.cos(tmp3) + xt0 * Math.sin(tmp3);
        xt = xt0 * Math.cos(tmp3) - yt0 * Math.sin(tmp3);
    }
}
