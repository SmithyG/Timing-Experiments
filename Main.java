import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*
        //Timing experiment for histogram algorithm.
        long avgTime = 0;
        //For each data set, the test is ran 100 times, each time recording nanoTime taken in avgTime.
        //Input is generated randomly each time.
        for (int j = 0; j < 100; j++) {
            //Input size
            int[] number = new int[700];
            Random rand = new Random();
            //Generates appropriate testing input.
            for (int i = 0; i < number.length; i++) {
                number[i] = rand.nextInt((100 - 1) + 1) + 1;
            }
            //Timing begins and algorithm is ran.
            long start = System.nanoTime();
            System.out.println(histogram(number));
            //Timing stops after algorithm is complete and recorded time is added to avgTime.
            avgTime += System.nanoTime() - start;
        }
        //The overall average is calculated.
        System.out.println(avgTime/100); */

        /* JASON'S EXAMPLE TABLE
        int[][] distances = {
                {
                        0, 58, 184, 271, 378, 379
                },
                {
                        58, 0, 167, 199, 351, 382
                },
                {
                        184, 167, 0, 43, 374, 370
                }
        }; */

        System.out.println(closestCity(matrixHelper()));
    }

    //Currently O(n)
    public static String histogram(int[] array) {
        String[] histoLine = new String[10];
        histoLine[0] = "\n   1-10: ";
        histoLine[1] = "\n11 - 20: ";
        histoLine[2] = "\n21 - 30: ";
        histoLine[3] = "\n31 - 40: ";
        histoLine[4] = "\n41 - 50: ";
        histoLine[5] = "\n51 - 60: ";
        histoLine[6] = "\n61 - 70: ";
        histoLine[7] = "\n71 - 80: ";
        histoLine[8] = "\n81 - 90: ";
        histoLine[9] = "\n91- 100: ";
        for (int i = 0; i < array.length; i++) {
            histoLine[((array[i] - 1) / 10)] = histoLine[((array[i] - 1) / 10)].concat("*");
        }
       /* Below is the first implementation of the algorithm, however it was slow as the complexity was O(nlogn) + n.
       for (int i = 0; i < array.length; i++) {
            if (array[i] <= 10) {
                histoLine[0] = histoLine[0].concat("*");
            } else if (array[i] > 10 && array[i] <= 20) {
                histoLine[1] = histoLine[1].concat("*");
            } else if (array[i] > 20 && array[i] <= 30) {
                histoLine[2] = histoLine[2].concat("*");
            } else if (array[i] > 30 && array[i] <= 40) {
                histoLine[3] = histoLine[3].concat("*");
            } else if (array[i] > 40 && array[i] <= 50) {
                histoLine[4] = histoLine[4].concat("*");
            } else if (array[i] > 50 && array[i] <= 60) {
                histoLine[5] = histoLine[5].concat("*");
            } else if (array[i] > 60 && array[i] <= 70) {
                histoLine[6] = histoLine[6].concat("*");
            } else if (array[i] > 70 && array[i] <= 80) {
                histoLine[7] = histoLine[7].concat("*");
            } else if (array[i] > 80 && array[i] <= 90) {
                histoLine[8] = histoLine[8].concat("*");
            } else if (array[i] > 90 && array[i] <= 100) {
                histoLine[9] = histoLine[9].concat("*");
            }
        } */
        return Arrays.toString(histoLine);
    }

    public static String closestCity(int[][] distanceMatrix) {
        int[] minDistance;
        minDistance = new int[distanceMatrix.length];

        for (int i = 0; i < distanceMatrix.length; i++) {
            int temp;
            /*
             This below if statement currently prevents an out of bounds exception from being thrown.
             Currently the temp variable needs to be assigned with a value related to the matrix.
             */
            if (i == distanceMatrix.length-1) {
                temp = distanceMatrix[i][i - 1];
            } else {
                temp = distanceMatrix[i][i + 1];
            }
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                if (distanceMatrix[i][j] < temp && distanceMatrix[i][j] != 0) {
                    temp = distanceMatrix[i][j];
                }
            }
            minDistance[i] = temp;
        }
        return Arrays.toString(minDistance);
    }

    public static int[][] matrixHelper() {
        final int size = 5;
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == i) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = rand.nextInt((500 - 1) + 1) + 1;
                }
            }
        }
        return matrix;
    }
}
