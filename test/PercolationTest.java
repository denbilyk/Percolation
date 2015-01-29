import org.junit.*;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PercolationTest {
    private Random rnd = new Random();


    @Test
    public void test() {
        int rowSize = 5;
        Percolation percolation = new Percolation(rowSize);
        int valueC;
        int valueR;
        while (!percolation.percolates()) {
            do {
                valueR = rnd.nextInt(rowSize) + 1;
                valueC = rnd.nextInt(rowSize) + 1;
            } while (percolation.isOpen(valueR, valueC));
            percolation.open(valueR, valueC);
        }

        System.out.println("Results: ");
        print(percolation.getArray(), rowSize);
        System.out.println();
        System.out.println("Count: " + percolation.getOpened());
        System.out.println("Thresholds: " + (double) percolation.getOpened() / (rowSize * rowSize));

    }


    private void print(int[] array, int rowSize) {
        for (int j = 1; j <= rowSize; j++) {
            for (int i = 1; i <= rowSize; i++) {
                System.out.print(array[rowSize * (j - 1) + (i - 1)]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}