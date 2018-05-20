package com.mo.java8.entity;

import java.util.concurrent.RecursiveTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Lenovo
 * @Date: 2018/5/20
 * @Time: 8:17
 * @Description: 自定义并行拆分方案
 */

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }


    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftForkJoinSumCalculator = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftForkJoinSumCalculator.fork();
        ForkJoinSumCalculator rightForkJoinSumCalculator = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightForkJoinSumCalculator.compute();
        Long leftResult = leftForkJoinSumCalculator.join();
        return leftResult+rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;

    }
}
