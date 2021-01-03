import java.util.Arrays;

public class Point {
	private int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return String.format("<%d,%d>", x, y);
	}
	
	public static int compare(Point a, Point b) {
		if (a.x == b.x)
			return Integer.compare(a.y, b.y);
		return a.x > b.x ? 1 : -1;
	}
	
	public int compare2(Point a, Point b) {
		return compare(a, b);
	}
	
	public int compareTo(Point other) {
		return compare(this, other);
	}

	public static void main(String[] args) {
		Point[] a = {new Point(1, 4), new Point(1, 3),
				new Point(0, 4)};

		// With a static method:
		Arrays.sort(a, Point::compare);
		// The same as:
		Arrays.sort(a, (u,v) -> Point.compare(u,v));	
		
		// With a non-static method of a particular object
		Point p = new Point(0, 0);
		Arrays.sort(a, p::compare2);
		// The same as:
		Arrays.sort(a, (u,v) -> p.compare2(u,v));
		
		// With a non-static method of a type
		Arrays.sort(a, Point::compareTo);
		// Which is just like:
		Arrays.sort(a, (u,v) -> u.compareTo(v));
	
		System.out.println(Arrays.toString(a));
		// [<0,4>, <1,3>, <1,4>]
	}
}
