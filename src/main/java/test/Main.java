package test;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;
import static java.util.stream.Collectors.toMap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    static class Test {
        public int a = 0;

        public void add() {

            synchronized (this) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                a++;

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Test test = new Test();

        ExecutorService service = Executors.newFixedThreadPool(10);

        service.submit(() -> {
            while(true) {
                System.out.println("read : " + ++test.a);
            }
        });

        service.submit(() -> {
            System.out.println("write");
            test.add();
        });

    }

}
