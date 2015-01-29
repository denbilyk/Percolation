/**
 * @author denis.bilyk
 */
public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int elementsInSide;
    private final int[] opened;     // array to store opened sites
    private final int idxBvp;       // index bottom virtual point
    private final int idxTvp;       // index top virtual point

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N is below zero!");
        elementsInSide = N;
        int elementsAmount = N * N + 2;
        idxBvp = elementsAmount - 1;
        uf = new WeightedQuickUnionUF(elementsAmount);
        idxTvp = idxBvp - 1;
        opened = new int[elementsAmount - 2];
    }

    public void open(int i, int j) {
        if (i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        if (isOpen(i, j)) return;
        int id = getArrayId(i, j);
        opened[id] = 1;

        // connect with directions
        // top
        if (i - 1 > 0 && isOpen(i - 1, j)) {
            uf.union(getArrayId(i - 1, j), id);
        }
        //bottom
        if (i + 1 <= elementsInSide && isOpen(i + 1, j)) {
            uf.union(getArrayId(i + 1, j), id);
        }
        // left
        if (j - 1 > 0 && isOpen(i, j - 1)) {
            uf.union(getArrayId(i, j - 1), id);
        }
        //right
        if (j + 1 <= elementsInSide && isOpen(i, j + 1)) {
            uf.union(getArrayId(i, j + 1), id);
        }
        //connect with virtual site
        // top
        if (i == 1) uf.union(id, idxTvp);

        //bottom
        if (i == elementsInSide) uf.union(id, idxBvp);
    }

    int getOpened() {
        int count = 0;
        for (int item : opened) {
            if (item == 1) count++;
        }
        return count;
    }

    int[] getArray() {
        return opened;
    }

    public boolean isOpen(int i, int j) {
        int id = getArrayId(i, j);
        return opened[id] == 1;
    }

    public boolean isFull(int i, int j) {
        int id = getArrayId(i, j);
        return uf.connected(idxTvp, id);

    }

    public boolean percolates() {
        return uf.connected(idxTvp, idxBvp);
    }

    private int getArrayId(int i, int j) {
        return (i - 1) * elementsInSide + j - 1;  //first index i,j = {1,1}. i - row, j - column.
    }
}
