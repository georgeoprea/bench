package bench;

import hdd.HDDRandomAccess;

/**
 * Created by georgeoprea on 09/05/17.
 */
public class TestHDDRandomAccess {
    public static void main(String[] args) {
        HDDRandomAccess test = new HDDRandomAccess();
        //test.initialize(9);
        System.out.println("512B");
        test.run("r", "fs", 512);
        System.out.println(test.getResult());

        test.run("r", "ft", 512);
        System.out.println(test.getResult());

        test.run("w", 512);
        System.out.println(test.getResult());


        System.out.println("4kb");


        test.run("r", "fs", 1024 * 4);
        System.out.println(test.getResult());

        test.run("r", "ft", 1024 * 4);
        System.out.println(test.getResult());

        test.run("w", 1024 * 4);
        System.out.println(test.getResult());

        System.out.println("64kb");

        test.run("r", "fs", 1024 * 64);
        System.out.println(test.getResult());

        test.run("r", "ft", 1024 * 64);
        System.out.println(test.getResult());

        test.run("w", 1024 * 64);
        System.out.println(test.getResult());

        System.out.println();
        test.run("r", "fs", 1024 * 1024);
        System.out.println(test.getResult());

        test.run("r", "ft", 1024 * 1024);
        System.out.println(test.getResult());

        test.run("w", 1024 * 1024);
        System.out.println(test.getResult());
    }
}
