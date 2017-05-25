package bench;

import java.util.ArrayList;
import java.util.Random;

public class CPUBinarySearch {
    private static int key;
    private ArrayList<Integer> my_array;
    private int size;

    public static int recursiveBinarySearch(ArrayList<Integer> sortedArray, int start, int end) {

        if (start < end) {
            int mid = start + (end - start) / 2;
            if (key < sortedArray.get(mid)) {
                return recursiveBinarySearch(sortedArray, start, mid);

            } else if (key > sortedArray.get(mid)) {
                return recursiveBinarySearch(sortedArray, mid + 1, end);

            } else {
                return mid;
            }
        }
        return -(start + 1);
    }

    public void initialize(int size) {
        this.size = size;
        my_array = new ArrayList<Integer>(size);
        Random rand = new Random();
        for (int i = 0; i < my_array.size(); i++) {
            my_array.add(rand.nextInt(1000000000));
        }
    }

    public void run() {
        recursiveBinarySearch(my_array, 0, size);
    }

    public void run(Object o) {
    }

    public void clean() {
    }

}
