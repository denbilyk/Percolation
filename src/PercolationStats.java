/**
 * @author denis.bilyk
 */
public class PercolationStats {


    public PercolationStats(int N, int T) throws IllegalArgumentException {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
    }

    public double mean() {
        return 0;
    }                  // sample mean of percolation threshold


    public double stddev() {
        return 0;
    }             // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return 0;
    }        // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return 0;
    }           // high endpoint of 95% confidence interval

}
