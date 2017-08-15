import java.awt.Point;

public class Gui_storage {
	public Point[] arrayPoints;
	public Point[] gnomePoints; 

	public Gui_storage() {

		this.arrayPoints = this.villagePoints();
		this.gnomePoints = this.gPoints();
	}

	public Point[] villagePoints() {
		Point[] points = new Point[11];
		for (int i = 0; i < points.length; i++) {
			Point p = new Point();
			points[i] = p;
		}
		double count = 0.0;
		for (int i = 0; i < points.length; i++) {

			points[i].setLocation(250 * Math.cos((count / 10.0) * 2 * Math.PI),
					250 * Math.sin((count / 10.0) * 2 * Math.PI));
			count = count + 1.0;
		}
		return points;
	}
	
	public Point[] gPoints() {
		Point[] points = new Point[11];
		for (int i = 0; i < points.length; i++) {
			Point p = new Point();
			points[i] = p;
		}
		double count = 0.0;
		for (int i = 0; i < points.length; i++) {

			points[i].setLocation(50 * Math.cos((count / 10.0) * 2 * Math.PI),
					50 * Math.sin((count / 10.0) * 2 * Math.PI));
			count = count + 1.0;
		}
		return points;
	}

}