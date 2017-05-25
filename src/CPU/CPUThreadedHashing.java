package CPU;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bench.IBenchmark;

public class CPUThreadedHashing implements IBenchmark {

    volatile boolean running = true;
    private String result;

    @Override
    public void initialize(int size) {

    }

    @Override
    public void warmUp() {

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Object) instead");
    }
    //CLASSIC BRUTE FORCE


    @Override
    public void run(Object... options) {
        //Integer[] param = (Integer[]) options;

        // maximum text length
        int maxTextLength = (Integer) options[0];
        // thread pool size
        int nThreads = (Integer) options[1];
        // hash code
        int hashCode = (Integer) options[2];


        int length = 2;

        ExecutorService executor = Executors.newFixedThreadPool(nThreads); // .newCachedThreadPool();
        HashManager hasher = new HashManager();
        String text = "aa";
        while (running) {
            HashBreakerTask worker = new HashBreakerTask(hasher, text, hashCode);
            executor.execute(worker);
            worker.run();
            text = hasher.getNextString(text);
            worker.run();
            // stop searching
            if (length > maxTextLength) {
                running = false;
            }
            // reset string length
            if (text == null) {
                length++;
                text = "aa";
                for (int i = 2; i < length; ++i)
                    text += "a";
            }
        }
        System.out.println(result);
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

    }


    @Override
    public void clean() {
    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    @Override
    public void warmUp(Object option) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp(int option) {
        // TODO Auto-generated method stub

    }

    class HashBreakerTask implements Runnable {

        // used to compute hashes from strings
        private final HashManager hasher;
        // the string to be hashed
        private final String text;
        // the expected hash output
        private final int expected;

        public HashBreakerTask(HashManager hasher, String text, int expected) {
            this.hasher = hasher;
            this.text = text;
            this.expected = expected;
        }

        @Override
        public void run() {
            // if we found the hash
            if (expected == hasher.hash(text)) {
                running = false;
                result = text;
            }
        }
    }

    /**
     * Used to compute hashes from strings
     */
    class HashManager {

        private final String charSet = "abcdefghijklmnopqrstuvwxyz";

        public int hash(String text) {
            int a = 0;
            int b = 0;
            for (char c : text.toCharArray()) {
                int index = charSet.indexOf(c);
                if (index == -1)
                    index = charSet.length() + 1;
                for (int i = 0; i < 17; i++) {
                    a = a * -6 + b + 0x74FA - index;
                    b = b / 3 + a + 0x81BE - index;
                }
            }

            return (a ^ b) % Integer.MAX_VALUE;
        }

        public String getNextString(String text) {
            int[] index = new int[text.length()];
            int end = charSet.length() - 1;
            // convert string to table of indices
            for (int i = 0; i < text.length(); i++) {
                index[i] = charSet.indexOf(text.charAt(i));
            }
            // increment last character
            int j;
            for (j = text.length() - 1; j >= 0; j--) {
                if (index[j] + 1 <= end) {
                    index[j] += 1;
                    break;
                }
            }
            //j==text.length()-1)
            if (j < 0) //eg. zz
                return null;
            else {
                if (j < text.length() - 1)
                    for (int k = j + 1; k < text.length(); k++) {
                        index[k] = 0;
                    }
            }
            String s = "";
            for (int c = 0; c < index.length; c++) {
                s = s + charSet.charAt(index[c]);
            }
            //System.out.println(s);
            // convert back to string
            return s;
        }

        public String getRandomString(int length) {
            String text = "";
            Random rand = new Random();
            for (int i = 0; i < length; i++) {
                char c = charSet.charAt(rand.nextInt(charSet.length()));
                text += c;
            }

            return text;
        }
    }

}