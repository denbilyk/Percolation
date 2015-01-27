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
        while (!percolation.percolates()) {
            int valueR = rnd.nextInt(rowSize) + 1;
            int valueC = rnd.nextInt(rowSize) + 1;
            percolation.open(valueR, valueC);
            //System.out.println("Row: " + valueR + "  ==  Col: " + valueC);
            //print(percolation.getOpenedArray(), rowSize);
            //System.out.println();
        }

        System.out.println("Results: ");
        print(percolation.getOpenedArray(), rowSize);
        System.out.println();
        System.out.println("Count: " + percolation.getOpened());
        System.out.println("Thresholds: " + (double)percolation.getOpened()/(rowSize * rowSize));

    }


    private void print(byte[] array, int rowSize) {
        for (int j = 1; j <= rowSize; j++) {
            for (int i = 1; i <= rowSize; i++) {
                System.out.print(array[rowSize * (j - 1) + (i - 1)]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}