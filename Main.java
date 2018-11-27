import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*
        histoTest(10, 10, 5000, 5000);
        int histoProofArr[] = {1, 25, 11, 56, 72, 86, 42, 76, 20, 83};
        System.out.println(histogram(histoProofArr));
        */
        closestCityTiming(10,100,1500,1500);
        System.out.println("Second Test");
        fasterClosestCityTiming(10,100,1500,1500);
        //int testMatrix[][] = matrixHelper(3);
        //System.out.println(closestCity(testMatrix));
        //System.out.println(fasterClosestCity(testMatrix));
    }

    //matrixTest is a method designed to allow for timing experiments to be carried out based on parameters.
    //testSize denotes how many points of data are gathered.
    //startSize is the initial size of the test data.
    //jump is the amount the size of the test data changes with each increment in testSize.
    //noOfIterations denotes how many times to run each data set test.
    public static void fasterClosestCityTiming(int testSize, int noOfIterations, int startSize, int jump) {
        for (int n = 0; n < testSize; n++) {
            long avgTime = 0;
            for (int i = 0; i < noOfIterations; i++) {
                //Creates a new randomly generated matrix according to parameters.
                int testMatrix[][] = matrixHelper(startSize + (jump * n));
                //Timing starts
                long start = System.nanoTime();
                fasterClosestCity(testMatrix);
                //Timing stops and time taken is added to avgTime
                avgTime += (System.nanoTime() - start);
            }
            //Average time is calculated by dividing avgTime by the number of times the test in ran.
            System.out.println(avgTime / noOfIterations);
        }
    }

    //matrixTest is a method designed to allow for timing experiments to be carried out based on parameters.
    //testSize denotes how many points of data are gathered.
    //startSize is the initial size of the test data.
    //jump is the amount the size of the test data changes with each increment in testSize.
    //noOfIterations denotes how many times to run each data set test.
    public static void closestCityTiming(int testSize, int noOfIterations, int startSize, int jump) {
        for (int n = 0; n < testSize; n++) {
            long avgTime = 0;
            for (int i = 0; i < noOfIterations; i++) {
                //Creates a new randomly generated matrix according to parameters.
                int testMatrix[][] = matrixHelper(startSize + (jump * n));
                //Timing starts
                long start = System.nanoTime();
                closestCity(testMatrix);
                //Timing stops and time taken is added to avgTime
                avgTime += (System.nanoTime() - start);
            }
            //Average time is calculated by dividing avgTime by the number of times the test in ran.
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

    //closestCity is a method that traverses a matrix passed to it to find the shortest distances between cities.
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
                //Check if the current element in distanceMatrix is greater than the current recorded shortest distance.
                //And check if the current element is not zero
                if (distanceMatrix[i][j] < minDistance[i] && distanceMatrix[i][j] != 0) {
                    minDistance[i] = distanceMatrix[i][j];
                }
            }
        }
        return Arrays.toString(minDistance);
    }

    //fasterClosestCity is a revised version of closestCity that makes use of the matrix being symmetrical.
    //This means visiting all elements of the matrix is not required, resulting in theoretical faster execution.
    public static String fasterClosestCity(int[][] distanceMatrix) {
        int[] minDistance;
        minDistance = new int[distanceMatrix.length];

        //Assign each minDistance with a max value. Necessary for comparison
        for (int i = 0; i < minDistance.length; i++) {
            minDistance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = i + 1; j < distanceMatrix.length; j++) {
                int currentElement = distanceMatrix[i][j];
                if (currentElement < minDistance[i]) {
                    minDistance[i] = currentElement;
                }
                if (currentElement < minDistance[j]) {
                    minDistance[j] = currentElement;
                }
            }
        }
        return Arrays.toString(minDistance);
    }

    //matrixHelper is an algorithm used to create a matrix of a specified size to be used by the distance
    public static int[][] matrixHelper(final int size) {
        Random rand = new Random();
        //Initialise matrix of passed size.
        int[][] matrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //Generate a random int between 1 and 500
                int distance = rand.nextInt((500 - 1) + 1) + 1;
                if (j == i) {
                    //Zero values where j=i
                    matrix[i][j] = 0;
                } else {
                    //Symmetrical assignments
                    matrix[j][i] = distance;
                    matrix[i][j] = distance;
                }
            }
        }
        return matrix;
    }
}
