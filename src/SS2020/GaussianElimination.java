package SS2020;

public class GaussianElimination {

    private final double EPSILON = 1e-10;
    public double[] decideUniqueSolution(double[][] matrix, double[] rightVector) {
        int n = rightVector.length;

        for (int p = 0; p < n; p++) {

            int max  = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(matrix[i][p]) > Math.abs(matrix[max][p])) {
                    max = i;
                }
            }
            double[] temp = matrix[p];
            matrix[p] = matrix[max];
            matrix[max] = temp;
            double ts = rightVector[p];
            rightVector[p] = rightVector[max];
            rightVector[max] = ts;

            if (Math.abs(matrix[p][p]) <= EPSILON) {
                throw new RuntimeException("Matrix is singular or nearly singular!");
            }
            for (int i = p+ 1; i < n; i++) {
                double alpha = matrix[i][p]/matrix[p][p];
                rightVector[i] -= alpha * rightVector[p];
                for (int j = p; j < n; j++) {
                    matrix[i][j] -= alpha * matrix[i][j];
                }
            }
        }
        double[] x = new  double[n];
        for (int i = n-1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                sum += matrix[i][j] * x[j];
            }
            x[i] = (rightVector[i] - sum) / matrix[i][i];
        }
        return x;
    }



    public static void  main(String[] args) {
        double[][] A = {{0, 1, 1}, {2, 4, -2}, {0, 3, 15}};
        double[] b =  {4, 2, 36};
        double[] x = new GaussianElimination().decideUniqueSolution(A,b);
        for (int i = 0; i < b.length; i++) {
            System.out.print(x[i]);
        }
    }
}
