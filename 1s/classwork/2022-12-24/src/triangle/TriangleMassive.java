package triangle;

public class TriangleMassive {
    public static double plosh(Triangle[] triangles) {
        double out = 0;
        for (Triangle triangle : triangles) {
            out += triangle.plosh();
        }
        return out;
    }

    public static boolean atLeastTwoEquals(Triangle[] triangles) {
        for (int i = 0; i < triangles.length - 1; i++) {
            for (int j = i + 1; j < triangles.length; j++) {
                if (triangles[i].equals(triangles[j])) {
                    return true;
                }
            }
        }
        return false;
    }
}
