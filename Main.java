import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*
        histoTest(10, 10, 5000, 5000);
        int histoProofArr[] = {1, 25, 11, 56, 72, 86, 42, 76, 20, 83};
        System.out.println(histogram(histoProofArr));
        */
        //matrixTest(10, 10, 1000, 1000);
        int testMatrix[][] = matrixHelper(3);
        System.out.println(closestCity(testMatrix));
        System.out.println(fasterClosestCity(testMatrix));
    }

    public static void matrixTest(int testSize, int noOfIterations, int startSize, int jump) {
        for (int n = 0; n < testSize; n++) {
            long avgTime = 0;
            for (int i = 0; i < noOfIterations; i++) {
                int testMatrix[][] = matrixHelper(startSize + (jump * n));
                long start = System.nanoTime();
                closestCity(testMatrix);
                avgTime += (System.nanoTime() - start);
            }
            System.out.println(avgTime / noOfIterations);
        }
    }

    //Timing experiment for histogram algorithm.
    //testSize denotes how many points of data are gathered.
    //startSize is the initial size of the test data.
    //jump is the amount the size of the test data changes with each increment in testSize.
    //noOfIterations denotes how many times to run each data set test.
    public static void histoTest(int testSize, int noOfIterations, int startSize, int jump) {
        for (int n = 0; n < testSize; n++) {
            long avgTime = 0;
            for (int i = 0; i < noOfIterations; i++) {
                //Test data size is calculated.
                int[] number = new int[startSize + (jump * n)];
                //Input is generated randomly each time.
                Random rand = new Random();
                //Generates appropriate bounded testing input.
                for (int j = 0; j < number.length; j++) {
                    number[j] = rand.nextInt((100 - 1) + 1) + 1;
                }
                //Timing begins and algorithm is ran.
                long start = System.nanoTime();
                histogram(number);
                //Timing stops after algorithm is complete and recorded time is added to avgTime.
                avgTime += (System.nanoTime() - start);
            }
            //Calculates and then prints the average time taken for each iteration of the timing experiment.
            System.out.println(avgTime / noOfIterations);
        }
    }

    //Currently O(n)
    public static String histogram(int[] inputArray) {
        //Initialisation of histoLine array plus formatting entries
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

        //Loop to add asterisks to histoLine based on elements in array.
        for (int i = 0; i < inputArray.length; i++) {
            //Minus 1 from current inputArray element and divide by 10 to find which index of histoLine to append asterisk.
            histoLine[((inputArray[i] - 1) / 10)] = histoLine[((inputArray[i] - 1) / 10)] + "*";
        }
        return Arrays.toString(histoLine);
    }

    public static String closestCity(int[][] distanceMatrix) {
        //Initialise return variable minDistance as an array of ints.
        int[] minDistance;
        //Set minDistance size equal to the length of the distance matrix. (Each row has one minimum distance)
        minDistance = new int[distanceMatrix.length];

        //Assign each minDistance with a max value. Necessary for comparison
        for (int i = 0; i < minDistance.length; i++) {
            minDistance[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix.length; j++) {
                if (distanceMatrix[i][j] < minDistance[i] && distanceMatrix[i][j] != 0) {
                    minDistance[i] = distanceMatrix[i][j];
                }
            }
        }
        return Arrays.toString(minDistance);
    }

    public static String fasterClosestCity(int[][] distanceMatrix) {
        int[] minDistance;
        minDistance = new int[distanceMatrix.length];

        //Assign each minDistance with a max value. Necessary for comparison
        for (int i = 0; i < minDistance.length; i++) {
            minDistance[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = i + 1; j < distanceMatrix.length; j++) {
                if (distanceMatrix[i][j] < minDistance[i]) {
                    minDistance[i] = distanceMatrix[i][j];
                }
                if (distanceMatrix[i][j] < minDistance[j]) {
                    minDistance[j] = distanceMatrix[i][j];
                }
            }
        }
        return Arrays.toString(minDistance);
    }

    //matrixHelper is an algorithm used to create a matrix of a specified size to be used by the distance
    public static int[][] matrixHelper(final int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int distance = rand.nextInt((500 - 1) + 1) + 1;
                if (j == i) {
                    matrix[i][j] = 0;
                } else {
                    matrix[j][i] = distance;
                    matrix[i][j] = distance;
                }
            }
        }
        return matrix;
    }
}
