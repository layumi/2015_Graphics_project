
public class Edge {
	
	int x0, y0, x1, y1, a, b;
	double delta;
	
	public Edge(int x0, int y0, int x1, int y1) {
		if (y0 > y1) {
			int temp = x0; x0 = x1; x1 = temp;
			temp = y0; y0 = y1; y1 = temp;
		}
		this.x0 = x0; this.y0 = y0;
		this.x1 = x1; this.y1 = y1;
		a = y0 - y1; b = x1 - x0;
		delta = - b / (double)a;
	}
	
	public void swap(Edge e) {
		int temp;
		temp = x0; x0 = e.x0; e.x0 = temp;
		temp = y0; y0 = e.y0; e.y0 = temp;
		temp = x1; x1 = e.x1; e.x1 = temp;
		temp = y1; y1 = e.y1; e.y1 = temp;
		temp = a; a = e.a; e.a = temp;
		temp = b; b = e.b; e.b = temp;
		double d;
		d = delta; delta = e.delta; e.delta = d;
	}
	
	public void copy(Edge e) {
		x0 = e.x0;
		y0 = e.y0;
		x0 = e.x1;
		y0 = e.y1;
		x0 = e.a;
		y0 = e.b;
		delta = e.delta;
	}
}
