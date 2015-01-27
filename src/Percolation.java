/**
 * @author denis.bilyk
 */
public class Percolation {
    private final byte[] opened;
    private final WeightedQuickUnionUF uf;
    private final int elementsInRow;
    private final int lastIndex;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N is below zero!");
        elementsInRow = N;
        int elementsAmount = N * N + 2;
        lastIndex = elementsAmount - 1;
        uf = new WeightedQuickUnionUF(elementsAmount);
        opened = new byte[elementsAmount];
        init(elementsAmount);
        createVirtualPoints(0, lastIndex);
    }

    public void open(int i, int j) {
        if (i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        if (!isOpen(i, j)) {
            int id = getArrayId(i, j);
            opened[id] = 1;
            if (i - 1 > 0 && isOpen(i - 1, j)) {
                uf.union(id, getArrayId(i - 1, j));
            }
            if (i + 1 < elementsInRow && isOpen(i + 1, j)) {
                uf.union(id, getArrayId(i + 1, j));
            }
            if (j - 1 > 0 && isOpen(i, j - 1)) {
                uf.union(id, getArrayId(i, j - 1));
            }
            if (j + 1 < elementsInRow && isOpen(i, j + 1)) {
                uf.union(id, getArrayId(i, j + 1));
            }
        }

    }


    int getOpened() {
        int counter = 0;
        for (byte b : opened) {
            if (b == 1) counter++;
        }
        return counter;
    }

    // open site (row i, column j) if it is not open already
    public boolean isOpen(int i, int j) {
        return opened[(i - 1) * elementsInRow + (j - 1)] == 1;
    }

    public boolean isFull(int i, int j) {
        return false;
        // is site (row i, column j) full?
    }

    public boolean percolates() {
        return uf.find(lastIndex) == uf.find(0);
    }

    byte[] getOpenedArray() {
        return opened;
    }

    private int getArrayId(int i, int j) {
        return (i - 1) * elementsInRow + (j - 1) + 1;
    }

    private void createVirtualPoints(int topPoint, int bottomPoint) {
        for (int i = 0, j = bottomPoint - 1; i < elementsInRow; i++, j--) {
            uf.union(topPoint, i + 1);
            uf.union(bottomPoint, j);
        }
    }

    private void init(int number) {
        for (int i = 0; i < number; i++) {
            opened[i] = 0;
        }
    }

}
