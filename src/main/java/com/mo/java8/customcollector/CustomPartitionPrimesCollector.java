package com.mo.java8.customcollector;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Lenovo
 * @Date: 2018/5/19
 * @Time: 21:01
 * @Description: 自定义筛选质数的收集器
 */

public class CustomPartitionPrimesCollector implements
        Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return ()->new HashMap<Boolean, List<Integer>>(16){{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> acc,Integer candidate)->
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean,List<Integer>> mapFirst,Map<Boolean,List<Integer>> mapSecond)->{
            mapFirst.get(true).addAll(mapSecond.get(true));
            mapFirst.get(false).addAll(mapSecond.get(false));
            return mapFirst;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    private static boolean isPrime(List<Integer> primes,Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    private static <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
        int i=0;
        for (A item : list) {
            if (!predicate.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
