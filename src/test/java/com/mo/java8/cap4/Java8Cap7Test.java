package com.mo.java8.cap4;

import com.mo.java8.entity.ForkJoinSumCalculator;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Lenovo
 * @Date: 2018/5/20
 * @Time: 8:15
 * @Description: java8新特性第7章
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log4j2
public class Java8Cap7Test {

    class XandY{
        private int X;
        private int Y;

        @Override
        public String toString() {
            return "XandY{" +
                    "X=" + X +
                    ", Y=" + Y +
                    '}';
        }

        public XandY(int x, int y) {
            X = x;
            Y = y;
        }

        public int getX() {

            return X;
        }

        public void setX(int x) {
            X = x;
        }

        public int getY() {
            return Y;
        }

        public void setY(int y) {
            Y = y;
        }
    }


    private long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> sumTask = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(sumTask);
    }


    @Test
    public void testse() {
        long result = Java8Cap4Test.measureSumPref(this::forkJoinSum, 10_000_000L);
        log.info("result:"+result);
    }

    @Test
    public void test001(){
        List<XandY> xandies = Arrays.asList(new XandY(1, 2), new XandY(2, 3), new XandY(9, 1), new XandY(1, 1), new
                XandY(0, 1));
        List<XandY> collect = xandies.stream().filter(xandY -> xandY.getY() + xandY.getX() >= 10).collect(Collectors
                .toList());
        log.info("collect:{}",collect.toString());
    }
}
