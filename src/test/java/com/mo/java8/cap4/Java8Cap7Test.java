package com.mo.java8.cap4;

import com.mo.java8.entity.ForkJoinSumCalculator;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
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
@Log
public class Java8Cap7Test {


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
}
