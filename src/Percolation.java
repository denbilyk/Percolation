/**
 * @author denis.bilyk
 */
public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int elementsInRow;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N is below zero!");
        elementsInRow = N;
        int elementsAmount = N*N + 2;
        int lastIndex = elementsAmount - 1;
        uf = new WeightedQuickUnionUF(elementsAmount);
        createVirtualPoints(0, lastIndex);
    }

    public void open(int i, int j) {
        if (i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        if (!isOpen(i, j)) {
            uf.union(i, j);
        }
    }


    // open site (row i, column j) if it is not open already
    public boolean isOpen(int i, int j) {

        // is site (row i, column j) open?
        return false;
    }

    public boolean isFull(int i, int j) {
        return false;
        // is site (row i, column j) full?
    }

    public boolean percolates() {
        return false;
    }

    private void createVirtualPoints(int topPoint, int bottomPoint) {
        for (int i = 0, j = bottomPoint - 1; i < elementsInRow; i++, j--) {
            uf.union(topPoint, i+1);
            uf.union(bottomPoint, j);
        }
    }

}
