import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {


    @Test
    public void test() {
        int rowSize = 5;
        Percolation percolation = new Percolation(rowSize);
    }


    private void print(int[] array, int rowSize) {
        for (int j = 0; j < rowSize; j++) {
            for (int i = 0; i < rowSize; i++) {
                System.out.print(array[rowSize*j + i]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}