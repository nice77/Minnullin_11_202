package triangle;

public class Triangle {
    private final double baseX1, baseY1;
    private final double baseX2, baseY2;
    private final double x0, y0;
    public static final double EPS = 1e-8;
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.baseX1 = x1;
        this.baseY1 = y1;
        this.baseX2 = x2;
        this.baseY2 = y2;
        this.x0= x3;
        this.y0 = y3;
    }
    public double baseCalc() {
        double squaredDiffByX, squaredDiffByY;
        squaredDiffByX = Math.abs(this.baseX1 * this.baseX1 - this.baseX2 * this.baseX2);
        squaredDiffByY = Math.abs(this.baseY1 * this.baseY1 - this.baseY2 * this.baseY2);
        return Math.sqrt(squaredDiffByX + squaredDiffByY);
    }
    public double sideCalc() {
        double squaredDiffByX, squaredDiffByY;
        squaredDiffByX = Math.abs(this.baseX1 * this.baseX1 - this.x0 * this.x0);
        squaredDiffByY = Math.abs(this.baseY1 * this.baseY1 - this.y0 * this.y0);
        return Math.sqrt(squaredDiffByX + squaredDiffByY);
    }

    public double perimeter() {
        double base = baseCalc();
        double side = sideCalc();
        return base + 2  * side;
    }

    public double plosh() {
        double base = baseCalc();
        double side = sideCalc();
        double height = Math.sqrt(side * side - (base / 2) * (base / 2));
        return base * height;
    }

    public boolean equals(Triangle other) {
        if (Math.abs(this.sideCalc() - other.sideCalc()) > EPS) {
            return false;
        }
        else if (Math.abs(this.baseCalc() - other.baseCalc()) > EPS) {
            return false;
        }
        return true;
    }

    public double[] getRadians() {
        double[] radians = new double[2];
        double base = baseCalc();
        double side = sideCalc();
        radians[0] = Math.acos((base / 2) / side);
        radians[1] = Math.asin((base / 2) / side);
        return radians;
    }

    public String toString() {
        String firstPart = "Base coords: (" + this.baseX1 + "; " + this.baseY1 + ") (" + this.baseX2 + "; " + this.baseY2 + ")";
        String secondPart = "Left coord: (" + this.x0 + "; " + this.y0 + ")";
        return firstPart + secondPart;
    }
}
