import java.lang.Math;

public class Main {
    public static double dist(double x1, double y1, double x2, double y2) {
        double dist_x = x1 - x2;
        double dist_y = y1 - y2;
        return Math.sqrt(dist_x * dist_x + dist_y * dist_y);
    }

    public static void main(String[] args) {
        System.out.println(dist(0.0f, 0.0f, 1.0f, 1.0f));
    }
}