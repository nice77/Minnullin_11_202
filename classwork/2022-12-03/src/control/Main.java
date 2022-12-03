package control;

class Main {
	public static void main(String[] args) {
		Line line = new Line(2, 6, 1, 9);
		Line line2 = new Line(1, 5, 4, 3);
		Line[] lines = new Lines[2];
		lines[0] = line;
		lines[1] = line2;
		System.out.println(line.cross(line2));
		System.out.println(line.getLength());
		System.out.println(line.getRectPl());
		System.out.println(LineMassive.getMassiveLength(lines));
		System.out.println(LineMassive.doubleCross(lines))
	}
}
