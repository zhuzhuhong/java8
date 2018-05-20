package com.mo.java8.cap4;

import com.mo.java8.customcollector.CustomPartitionPrimesCollector;
import com.mo.java8.entity.Dish;
import com.mo.java8.entity.Transaction;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Lenovo
 * @Date: 2018/5/19
 * @Time: 6:50
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log
public class Java8Cap4Test {

    static enum CaloriesLevel{
        DIET,NORMAL,FAT
    }

    @Test
    public void testGroupByCurrencyAndSum() {
        Map<Integer, List<Transaction>> collect = Transaction.TRANSACTIONS.stream().collect(Collectors.groupingBy
                (Transaction::getValue));

    }

    @Test
    public void testCompute() {
        Long collect = Dish.menu.stream().count();
        log.info(collect.toString());
        Assert.assertTrue(9==collect);
    }

    /**
     * 求最大值
     */
    @Test
    public void getMaxByCalories() {
        Optional<Dish> max = Dish.menu.stream().max(Comparator.comparingInt(Dish::getCalories));
        log.info(max.get().toString());
    }

    /**
     * 使用综合方法计算总和、平均值、最大值、最小值
     */
    @Test
    public void getSummarizing() {
        IntSummaryStatistics collect = Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        log.info(collect.toString());
    }

    /**
     * 使用专有方法拼接字科伦坡串
     */
    @Test
    public void getDishNameString() {
        String DishName = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        log.info(DishName);
    }

    /**
     * 使用reducing通用方法求和
     * 通用方法，自定义规则
     */
    @Test
    public void getSumByReducing() {
//        Integer caloriesSum = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        Integer caloriesSum = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        log.info(caloriesSum.toString());
    }

    /**
     * 使用reducing将菜单名拼接成字符串
     * 最大程度的自定义拼接规则
     */
    @Test
    public void getNameStringByReducing() {
        String nameString = Dish.menu.stream().collect(Collectors.reducing("", Dish::getName,
                (name1, name2) -> {
                    StringBuilder stringBuilder = new StringBuilder(name1);
                    if (name1.isEmpty()) {
                        stringBuilder.append(name2);
                    } else {
                        stringBuilder.append(", ").append(name2);
                    }
                    return stringBuilder.toString();
                }));
        log.info(nameString);
    }

    /**
     * 使用通用方法获取热量最高值
     * 自定义获取规则
     */
    @Test
    public void getMaxCaloriesByReducing() {
        Integer maxColaries = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories,
                (calories1, calories2) -> calories1 > calories2 ? calories1 : calories2));
        log.info("MaxCalories:"+maxColaries.toString());
    }

    /**
     * 使用reducing的单参数方法获取热量最高值
     */
    @Test
    public void getMaxCaloriesByReducingAndOneParam() {
        Optional<Dish> maxColariesDishOptional = Dish.menu.stream().collect(Collectors.reducing((dish1, dish2) -> dish1.getCalories()
                > dish2.getCalories()
                ? dish1 : dish2));
        log.info(maxColariesDishOptional.get().toString());
    }

    /**
     * 通过IntStream来计算热量
     */
    @Test
    public void getCaloriesSumByIntStream() {
        int caloriesSum = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        log.info(Integer.toString(caloriesSum));
    }

    @Test
    public void groupByType() {
        Map<Dish.Type, List<Dish>> collect = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        log.info(collect.toString());
    }

    @Test
    public void groupByCaloriesLevel() {
        Map<CaloriesLevel, List<Dish>> caloriesLevelListMap = Dish.menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloriesLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloriesLevel.NORMAL;
            } else {
                return CaloriesLevel.FAT;
            }
        }));

        log.info(caloriesLevelListMap.toString());
    }

    @Test
    public void groupByTypeAndCaloriesLevel() {
        Map<Dish.Type, Map<CaloriesLevel, List<Dish>>> collect = Dish.menu.stream().collect(Collectors.groupingBy
                (Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloriesLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloriesLevel.NORMAL;
            } else {
                return CaloriesLevel.FAT;
            }
        })));
        log.info(collect.toString());
    }

    @Test
    public void getMostCaloriesByType() {
        Map<Dish.Type, Dish> collect = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.collectingAndThen(
                        Collectors.maxBy(
                                Comparator.comparingInt(
                                        Dish::getCalories)),
                        Optional::get)));
        log.info(collect.toString());
    }

    /**
     * 按是否为素菜分区，只有是或者不是
     */
    @Test
    public void partition() {
        Map<Boolean, List<Dish>> listMap = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        log.info(listMap.toString());
    }

    /**
     * 先按是否为素菜分区，然后再按类型分组
     */
    @Test
    public void partitionAndGroupByType() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect = Dish.menu.stream().collect(Collectors.partitioningBy
                (Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        log.info(collect.toString());
    }

    /**
     * 使用自定义的收集器区分质数
     */
    @Test
    public void partitionPrimesByCustomCollector() {
        Map<Boolean, List<Integer>> map = IntStream.rangeClosed(2, 100).boxed().collect(new
                CustomPartitionPrimesCollector());
        log.info(map.toString());
    }

    @Test
    public void collectorHarness() {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            IntStream.rangeClosed(2, 1_000_000).boxed().collect(new CustomPartitionPrimesCollector());
            long duration = (System.nanoTime() - startTime)/1_000_000;
            if (duration<fastest){
                fastest = duration;
            }
        }
        log.info("Fastest execution done it "+fastest+" msecs");
    }


    public static long measureSumPref(Function<Long, Long> function, Long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            Long sum = function.apply(n);
            long duration = (System.nanoTime()-startTime)/n;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    /**
     * 使用顺序的方法执行求和操作
     * @param n
     * @return
     */
    private long sequnsSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L,Long::sum);
    }

    /**
     * 将流转为并行流
     * @param n
     * @return
     */
    private long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L,Long::sum);
    }

    private long longStreamSum(long n) {
        return LongStream.rangeClosed(1L, n).parallel().reduce(0L, Long::sum);
    }

    @Test
    public void measureSumSecond() {
        //long sequn = measureSumPref(this::sequnsSum, 10_000_000L);
        long parallel = measureSumPref(this::parallelSum, 10_000_000L);
       // log.info("sequn:"+sequn);
        log.info("parallel:"+parallel);
    }

    @Test
    public void LongStreamSum() {
        log.info("LongStream:"+measureSumPref(this::longStreamSum,10_000_000L));
    }




}
