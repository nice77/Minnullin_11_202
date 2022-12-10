package control;

class LineMassive {
	public static double getMassiveLength(Line[] lines) {
		double out = 0;
		for (Line line : lines) {
			out += line.getLength();
		}
		return out;
	}
	public static boolean doubleCross(Line[] lines) {
		for (int i = 0; i < lines.length - 1; i++) {
			if (lines[i].cross(lines[i + 1])) {
				return true;
			}
		}
		return false;
	}
}
