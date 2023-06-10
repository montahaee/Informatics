package cutting.utility;

public class MyMath {

    public static boolean almostEqual(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }

    public static boolean almostEqual(double a, double b) {
        return almostEqual(a, b,0.0000000001);
    }

    public static double round(double d) {
        return Math.round((d * 1000000)) /1000000.0;
    }

    public static boolean isBounded(double a, double boundaries) {
        return Math.abs(a) < boundaries;
    }
}
