/**
 * @author denis.bilyk
 */
public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int N;
    private final int[] opened;     // array to store opened sites
    private final int idxTvp;       // index top virtual point
    //private final int idxBvp;       // index bottom virtual point

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N is below zero!");
        this.N = N;
        int elementsAmount = N * N + 2;
        uf = new WeightedQuickUnionUF(elementsAmount);
        idxTvp = elementsAmount - 2;
        opened = new int[elementsAmount - 2];
        //idxBvp = elementsAmount - 1;
    }

    public void open(int i, int j) {
        checkInputs(i, j);
        if (isOpen(i, j)) return;
        int id = getArrayId(i, j);
        opened[id] = 1;

        // connect with directions
        // top
        int top = i - 1;
        if (top > 0 && isOpen(top, j)) {
            uf.union(getArrayId(top, j), id);
        }
        //bottom
        int bottom = i + 1;
        if (bottom <= N && isOpen(bottom, j)) {
            uf.union(getArrayId(bottom, j), id);
        }
        // left
        int left = j - 1;
        if (left > 0 && isOpen(i, left)) {
            uf.union(getArrayId(i, left), id);
        }
        //right
        int right = j + 1;
        if (right <= N && isOpen(i, right)) {
            uf.union(getArrayId(i, right), id);
        }
        //connect with virtual site
        // top
        if (i == 1) uf.union(id, idxTvp);

        //bottom
        //if (i == N) uf.union(id, idxBvp); // Will be affect not full bottom sites
    }

    public boolean isOpen(int i, int j) {
        checkInputs(i, j);
        int id = getArrayId(i, j);
        return opened[id] == 1;
    }

    public boolean isFull(int i, int j) {
        checkInputs(i, j);
        int id = getArrayId(i, j);
        return uf.connected(idxTvp, id);

    }

    public boolean percolates() {
        boolean connected = false;
        for (int i = N * N - 1; i >= N * (N - 1); i--) {
            connected = uf.connected(idxTvp, i);
        }
        return connected;
        //return uf.connected(idxTvp, idxBvp); //Will be affect not full bottom sites
    }

    private void checkInputs(int i, int j) {
        if (i <= 0 || j <= 0 || i > N || j > N) throw new IndexOutOfBoundsException();
    }

    private int getArrayId(int i, int j) {
        return (i - 1) * N + j - 1;  //first index i,j = {1,1}. i - row, j - column.
    }
}
